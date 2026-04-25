package ru.javarush.pastukhov.animalisland;

import ru.javarush.pastukhov.animalisland.config.GameConfig;
import ru.javarush.pastukhov.animalisland.entity.Animals;
import ru.javarush.pastukhov.animalisland.entity.Cell;
import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Island;
import ru.javarush.pastukhov.animalisland.model.PopulationRecord;
import ru.javarush.pastukhov.animalisland.service.*;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ru.javarush.pastukhov.animalisland.util.TranslationUtil.capitalize;

public class Simulation {
    private static final Logger LOGGER = Logger.getLogger(Simulation.class.getName());

    private final Island island = new Island();
    private final AnimalSpawner spawner = new AnimalSpawner();
    private final AnimalMover mover = new AnimalMover();
    private final CellProcessor processor = new CellProcessor();
    private final StatisticsCollector statsCollector = new StatisticsCollector();
    private final IslandRenderer renderer = new IslandRenderer();

    private int currentTurn = 0;
    private final List<PopulationRecord> history = new ArrayList<>();

    public Simulation() {
        spawner.spawnAll(island);
    }

    public void run() {
        renderer.render(island);

        // Меняем счётчик: теперь игра может закончиться раньше
        while (!isGameOver()) {
            LOGGER.info("--- Ход " + (++currentTurn) + " ---");
            processTurn();
            renderer.render(island);
            printCurrentStats();
            sleep();

            // Ограничиваем максимальное количество ходов (на всякий случай)
            if (currentTurn >= GameConfig.getMaxTurns()) {
                LOGGER.warning("Достигнуто максимальное количество ходов: " + GameConfig.getMaxTurns());
                break;
            }
        }

        printFinalStats();
    }

    private boolean isGameOver() {
        String condition = GameConfig.getStopCondition();

        return switch (condition.toLowerCase()) {
            case "nofood" -> noFoodCondition();
            case "alldead" -> allAnimalsDead();
            case "maxturns" -> currentTurn >= GameConfig.getMaxTurns(); // уже есть в цикле, но можно дублировать
            default -> throw new IllegalStateException("Неизвестное условие остановки: " + condition);
        };
    }

    private boolean noFoodCondition() {
        boolean hasPlants = false;
        boolean hasHerbivores = false;

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                Cell cell = island.getCell(x, y);
                if (cell.getPlants().getCurrentCount() > 0) {
                    hasPlants = true;
                }
                for (Animals animal : cell.getAnimals()) {
                    if (animal instanceof Herbivores) {
                        hasHerbivores = true;
                    }
                }
            }
        }

        return !hasPlants && !hasHerbivores;
    }

    private boolean allAnimalsDead() {
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                if (!island.getCell(x, y).getAnimals().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void processTurn() {
        mover.moveAll(island, getAllAnimals());
        processor.processAll(island);
        history.add(statsCollector.collect(island, currentTurn));
    }

    private List<Animals> getAllAnimals() {
        List<Animals> list = new ArrayList<>();
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                list.addAll(island.getCell(x, y).getAnimals());
            }
        }
        return list;
    }

    private void printCurrentStats() {
        PopulationRecord last = history.get(history.size() - 1);
        LOGGER.info(String.format("Ход %d | ", last.turn()) +
                last.animalCounts().entrySet().stream()
                        .map(e -> capitalize(TranslationUtil.toNomPlural(e.getKey())) + "=" + e.getValue())
                        .reduce((a, b) -> a + " " + b)
                        .orElse("") +
                " | Растения=" + last.plants());
    }

    private void printFinalStats() {
        LOGGER.info("\n" + "=".repeat(80));
        LOGGER.info("                     ФИНАЛЬНАЯ СТАТИСТИКА");
        LOGGER.info("=".repeat(80));

        if (history.isEmpty()) return;

        PopulationRecord last = history.get(history.size() - 1);

        StringBuilder header = new StringBuilder();
        header.append(String.format("%-6s", "Ход"));
        last.animalCounts().keySet().forEach(type ->
                header.append(String.format(" | %-10s", capitalize(TranslationUtil.toNomPlural(type))))
        );
        header.append(String.format(" | %-10s", "Растения"));
        LOGGER.info(header.toString());
        LOGGER.info("-".repeat(80));

        for (PopulationRecord record : history) {
            StringBuilder row = new StringBuilder();
            row.append(String.format("%-6d", record.turn()));
            last.animalCounts().keySet().forEach(type ->
                    row.append(String.format(" | %-10d", record.animalCounts().getOrDefault(type, 0)))
            );
            row.append(String.format(" | %-10d", record.plants()));
            LOGGER.info(row.toString());
        }
        LOGGER.info("=".repeat(80));

        if (isGameOver()) {
            LOGGER.warning("ИГРА ОКОНЧЕНА: экосистема погибла — нет растений и травоядных.");
        } else {
            LOGGER.info("ИГРА ЗАВЕРШЕНА по достижении лимита ходов.");
        }
    }

    private void sleep() {
        try {
            Thread.sleep(GameConfig.getTickDelay());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.warning("Симуляция прервана");
        }
    }
}