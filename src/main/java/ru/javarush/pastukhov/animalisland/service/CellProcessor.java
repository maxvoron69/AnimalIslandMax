package ru.javarush.pastukhov.animalisland.service;

import ru.javarush.pastukhov.animalisland.config.PredatorFoodConfig;
import ru.javarush.pastukhov.animalisland.entity.*;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CellProcessor {
    private static final Logger LOGGER = Logger.getLogger(CellProcessor.class.getName());

    private final ExecutorService workerPool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors(),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
    );

    public void shutdown() {
        workerPool.shutdown();
        try {
            if (!workerPool.awaitTermination(5, TimeUnit.SECONDS)) {
                workerPool.shutdownNow();
                if (!workerPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    LOGGER.warning("Пул потоков CellProcessor не завершился");
                }
            }
        } catch (InterruptedException e) {
            workerPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void processAll(Island island, int currentTurn) {

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                workerPool.submit(() -> {
                    try {
                        Cell cell = island.getCell(finalX, finalY);
                        if (cell != null) {
                            process(cell, finalX, finalY, currentTurn);
                        }
                    } catch (Exception e) {
                        LOGGER.warning(String.format(
                                "Ошибка при обработке клетки (%d, %d): %s", finalX, finalY, e.getMessage()
                        ));
                        if (e instanceof RuntimeException && !(e instanceof IllegalArgumentException)) {
                            LOGGER.log(java.util.logging.Level.SEVERE, "Стек вызовов:", e);
                        }
                    }
                });
            }
        }
    }

    public void process(Cell cell, int x, int y, int currentTurn) {
        List<Animals> animals = new ArrayList<>(cell.getAnimals());

        // --- Охота ---
        for (Animals predator : animals) {
            if (predator instanceof Predators p) {
                Set<String> possiblePreyTypes = PredatorFoodConfig.getPreyTypes(predator.getType());
                for (String preyType : possiblePreyTypes) {
                    boolean hasPreyInCell = cell.getAnimals().stream()
                            .anyMatch(a -> a.getType().equals(preyType) && !a.equals(predator));
                    if (hasPreyInCell) {
                        p.hunt(preyType, cell);
                    }
                }
            }
        }

        // --- Еда для травоядных ---
        for (Animals herbivore : animals) {
            if (herbivore instanceof Herbivores && cell.hasPlantsAvailable()) {
                ((Herbivores) herbivore).setCurrentCell(cell);
                boolean ate = herbivore.eat();
                if (ate) {
                    cell.consumePlant();
                }
            }
        }

        // --- Рост растений ---
        cell.growPlant(currentTurn);

        // --- Размножение ---
        for (Animals animal : new ArrayList<>(cell.getAnimals())) {
            Organism child = animal.reproduce(cell);
            if (child != null) {
                cell.addAnimal((Animals) child);
            }
        }

        // --- Конец дня ---
        for (Animals animal : animals) {
            if (animal instanceof Predators p) p.endOfDay();
            if (animal instanceof Herbivores h) h.endOfDay();
            animal.starve();
        }

        // --- Удаление мёртвых ---
        List<Animals> toRemove = new ArrayList<>();
        for (Animals animal : animals) {
            if (!animal.isAlive()) {
                toRemove.add(animal);
                LOGGER.info(TranslationUtil.toNominativ(animal.getType()) +
                        " умер(ла) от голода в клетке (" + x + ", " + y + ")");
            }
        }
        for (Animals dead : toRemove) {
            cell.removeAnimal(dead);
        }
    }
}