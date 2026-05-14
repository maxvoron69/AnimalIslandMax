package ru.javarush.pastukhov.animalisland.service;

import ru.javarush.pastukhov.animalisland.config.PredatorFoodConfig;
import ru.javarush.pastukhov.animalisland.entity.*;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class CellProcessor {
    private static final Logger LOGGER = Logger.getLogger(CellProcessor.class.getName());

    public void processAll(Island island) {
        List<CellTask> tasks = new ArrayList<>();

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                tasks.add(new CellTask(x, y));
            }
        }

        tasks.parallelStream().forEach(task -> {
            try {
                Cell cell = island.getCell(task.x, task.y);
                if (cell != null) {
                    process(cell, task.x, task.y);
                }
            } catch (Exception e) {
                LOGGER.warning(String.format("Ошибка при обработке клетки (%d, %d): %s", task.x, task.y, e.getMessage()));
                if (e instanceof RuntimeException && !(e instanceof IllegalArgumentException)) {
                    LOGGER.log(java.util.logging.Level.SEVERE, "Стек вызовов:", e);
                }
            }
        });
    }

    private static class CellTask {
        final int x, y;
        CellTask(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void process(Cell cell, int x, int y) {
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
                    cell.consumePlant();                 }
            }
        }

        // --- Рост растений ---
        cell.growPlant();

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