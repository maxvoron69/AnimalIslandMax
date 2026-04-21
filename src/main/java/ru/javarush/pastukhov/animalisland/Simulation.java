package ru.javarush.pastukhov.animalisland;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.config.GameConfig;
import ru.javarush.pastukhov.animalisland.config.PlantConfig;
import ru.javarush.pastukhov.animalisland.entity.*;
import ru.javarush.pastukhov.animalisland.util.Direction;
import ru.javarush.pastukhov.animalisland.util.GameUtils;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.*;
import java.util.logging.Logger;

public class Simulation {
    private final Island island;
    private int currentTurn = 0;
    private static final Logger LOGGER = Logger.getLogger(Simulation.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private static final int CELL_WIDTH = 20;
    private final List<PopulationRecord> populationHistory = new ArrayList<>();

    public Simulation() {
        this.island = new Island();
        initializeAnimals();
    }

    private void initializeAnimals() {

        System.out.println("Инициализация животных начинается.");

        List<Animals> animals = new ArrayList<>();

        for (String animalType : AnimalConfig.getAnimalTypes()) {
            Class<? extends Animals> animalClass = getAnimalClass(animalType);
            for (int i = 0; i < GameConfig.getInitialQuantityAnimal(animalType); i++) {
                try {
                    Animals animal = animalClass.getConstructor(int.class).newInstance(1);
                    animals.add(animal);
                } catch (Exception e) {
                    LOGGER.severe("Не удалось создать экземпляр: " + animalType);
                }
            }
        }

        for (Animals animal : animals) {
            int x = GameUtils.RANDOM.nextInt(island.getWidth());
            int y = GameUtils.RANDOM.nextInt(island.getHeight());
            island.getCell(x, y).addAnimal(animal);
        }

        System.out.println("Инициализация завершена. Животные размещены.");
    }

    public void run() {
        printIsland();

        while (currentTurn < 5) {
            System.out.println("\n--- Ход " + (++currentTurn) + " ---");
            processTurn();
            printIsland();
            printStatistics();

            try {
                Thread.sleep(GameConfig.getTickDelay());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        printFinalStatistics();
    }

    private void processTurn() {
        moveAllAnimals();
        processAllCells();
        collectStatistics();
    }

    private void moveAllAnimals() {
        List<Animals> allAnimals = getAllAnimals();
        Collections.shuffle(allAnimals); // случайный порядок

        for (Animals animal : allAnimals) {
            int[] pos = findAnimalPosition(animal);
            if (pos != null) {
                moveAnimal(animal, pos[0], pos[1]);
            }
        }
    }

    private void moveAnimal(Animals animal, int fromX, int fromY) {
        int currentX = fromX;
        int currentY = fromY;

        for (int step = 0; step < animal.getSpeed(); step++) {
            Direction direction = animal.chooseDirection();
            if (direction == Direction.NONE) break;

            int newX = currentX, newY = currentY;

            switch (direction) {
                case UP -> newY = Math.max(0, currentY - 1);
                case DOWN -> newY = Math.min(island.getHeight() - 1, currentY + 1);
                case LEFT -> newX = Math.max(0, currentX - 1);
                case RIGHT -> newX = Math.min(island.getWidth() - 1, currentX + 1);
            }

            Cell fromCell = island.getCell(currentX, currentY);
            Cell toCell = island.getCell(newX, newY);

            fromCell.getAnimals().remove(animal);
            toCell.addAnimal(animal);

            LOGGER.info(animal.getLocalizedType() + " сделал шаг: " + direction +
                    " → (" + newX + ", " + newY + ")");

            currentX = newX;
            currentY = newY;
        }
    }

    private List<Animals> getAllAnimals() {
        List<Animals> animals = new ArrayList<>();
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                animals.addAll(island.getCell(x, y).getAnimals());
            }
        }
        return animals;
    }

    private int[] findAnimalPosition(Animals animal) {
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                if (island.getCell(x, y).getAnimals().contains(animal)) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    private void processAllCells() {
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                processCell(x, y);
            }
        }
    }

    private void processCell(int x, int y) {
        Cell cell = island.getCell(x, y);
        List<Animals> animals = new ArrayList<>(cell.getAnimals()); // копия для безопасности

        // --- Охота ---
        for (Animals predator : animals) {
            if (predator instanceof Predators) {
                for (Animals prey : animals) {
                    if (predator != prey) {
                        ((Predators) predator).eat(prey.getType());
                    }
                }
            }
        }

        // --- Еда ---
        Plant plant = cell.getPlants();
        for (Animals herbivore : animals) {
            if (herbivore instanceof Herbivores && cell.hasPlantsAvailable()) {
                boolean ate = herbivore.eat();
                if (ate) {
                    Plant updated = cell.getPlants().consume();
                    cell.setPlants(updated);
                }
            }
        }

        // --- Размножение растений ---
        if (plant.canGrow() && GameUtils.RANDOM.nextDouble() < PlantConfig.getGrowthRate()) {
            Plant updated = (Plant) plant.createNewInstance();
            cell.setPlants(updated);
        }

        // --- Размножение ---
        for (Animals animal : new ArrayList<>(cell.getAnimals())) {
            Organism child = animal.reproduce();
            if (child != null) {
                cell.addAnimal((Animals) child);
            }
        }
    }

    public void printIsland() {
        System.out.println("\n=== ОСТРОВ (" + island.getWidth() + "x" + island.getHeight() + ") ===");
        for (int y = 0; y < island.getHeight(); y++) {
            System.out.print("[");
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = island.getCell(x, y);
                String cellContent = formatCell(cell);
                System.out.printf("%-10s", cellContent);
                if (x < island.getWidth() - 1) System.out.print(" | ");
            }
            System.out.println("]");
        }
        System.out.println("===================================");
    }

    private String formatCell(Cell cell) {
        if (cell == null) {
            return " ".repeat(CELL_WIDTH);
        }

        StringBuilder sb = new StringBuilder();

        // Животные
        for (Animals animal : cell.getAnimals()) {
            if (animal == null) continue;
            String emoji = AnimalConfig.getAnimalEmoji(animal.getType());
            sb.append(emoji).append(animal.getCurrentCount());
        }

        // Растения
        int plantCount = cell.getPlants().getCurrentCount();
        if (plantCount > 0) {
            sb.append(" ").append(PlantConfig.getEmoji()).append(plantCount);
        }

        String content = sb.length() > 0 ? sb.toString() : " ";

        // Выравнивание: обрезаем или дополняем пробелами до CELL_WIDTH
        if (content.length() > CELL_WIDTH) {
            content = content.substring(0, CELL_WIDTH); // Обрезаем, если слишком длинно
        } else {
            content = String.format("%-" + CELL_WIDTH + "s", content); // Дополняем пробелами справа
        }

        return content;
    }

    private Class<? extends Animals> getAnimalClass(String type) {
        return switch (type) {
            case "wolf" -> Wolf.class;
            case "sheep" -> Sheep.class;
            case "rabbit" -> Rabbit.class;
            case "fox" -> Fox.class;
            case "bear" -> Bear.class;
            case "boar" -> Boar.class;
            case "horse" -> Horse.class;
            case "deer" -> Deer.class;
            case "goat" -> Goat.class;
            case "duck" -> Duck.class;
            case "eagle" -> Eagle.class;
            case "mouse" -> Mouse.class;
            case "buffalo" -> Buffalo.class;
            case "boa" -> Boa.class;
            case "caterpillar" -> Caterpillar.class;
            default -> throw new IllegalArgumentException("Неизвестный тип животного: " + type);
        };
    }

    private void collectStatistics() {

        Map<String, Integer> counts = new HashMap<>();
        int totalPlants = 0;

        for (String type : AnimalConfig.getAnimalTypes()) {
            counts.put(type, 0);
        }

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                Cell cell = island.getCell(x, y);
                for (Animals animal : cell.getAnimals()) {
                    String type = animal.getType();
                    counts.merge(type, 1, Integer::sum); // увеличиваем счётчик
                }
                totalPlants += cell.getPlants().getCurrentCount();
            }
        }
        populationHistory.add(new PopulationRecord(currentTurn, counts, totalPlants));
    }

    private void printStatistics() {
        if (populationHistory.isEmpty()) return;

        PopulationRecord last = populationHistory.get(populationHistory.size() - 1);

        StringBuilder sb = new StringBuilder();
        sb.append("📊 Ход ").append(last.turn).append(" | ");

        for (String type : AnimalConfig.getAnimalTypes()) {
            int count = last.animalCounts.getOrDefault(type, 0);
            String shortName = switch (type) {
                case "wolf" -> "Волки";
                case "sheep" -> "Овцы";
                case "rabbit" -> "Зайцы";
                case "fox" -> "Лисы";
                case "bear" -> "Медведи";
                case "boar" -> "Кабаны";
                case "horse" -> "Лошади";
                case "deer" -> "Олени";
                case "goat" -> "Козы";
                case "duck" -> "Утки";
                case "eagle" -> "Орлы";
                case "mouse" -> "Мыши";
                case "buffalo" -> "Бизоны";
                case "boa" -> "Удавы";
                case "caterpillar" -> "Гусеницы";
                default -> Character.toUpperCase(type.charAt(0)) + type.substring(1);
            };
            sb.append(shortName).append(": ").append(count).append(" | ");
        }

        sb.append("Растения: ").append(last.plants);

        System.out.println(sb.toString());
    }

    private void printFinalStatistics() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                     ФИНАЛЬНАЯ СТАТИСТИКА");
        System.out.println("=".repeat(80));

        // Заголовок
        System.out.printf("%-4s", "Ход");
        for (String type : AnimalConfig.getAnimalTypes()) {
            System.out.printf(" | %-6s", type.substring(0, 1).toUpperCase()); // W, S, R...
        }
        System.out.printf(" | %-8s%n", "Растения");
        System.out.println("-".repeat(80));

        // Данные
        for (PopulationRecord record : populationHistory) {
            System.out.printf("%-4d", record.turn);
            for (String type : AnimalConfig.getAnimalTypes()) {
                int count = record.animalCounts.getOrDefault(type, 0);
                System.out.printf(" | %-6d", count);
            }
            System.out.printf(" | %-8d%n", record.plants);
        }
        System.out.println("=".repeat(80));
    }

    private static class PopulationRecord {
        final int turn;
        final Map<String, Integer> animalCounts;
        final int plants;

        PopulationRecord(int turn, Map<String, Integer> animalCounts, int plants) {
            this.turn = turn;
            this.animalCounts = new HashMap<>(animalCounts);
            this.plants = plants;
        }
    }
}