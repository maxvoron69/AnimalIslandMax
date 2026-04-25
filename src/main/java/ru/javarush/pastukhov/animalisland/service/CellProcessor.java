package ru.javarush.pastukhov.animalisland.service;

import ru.javarush.pastukhov.animalisland.config.PlantConfig;
import ru.javarush.pastukhov.animalisland.config.PredatorFoodConfig;
import ru.javarush.pastukhov.animalisland.entity.*;
import ru.javarush.pastukhov.animalisland.util.GameUtils;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class CellProcessor {
    private static final Logger LOGGER = Logger.getLogger(CellProcessor.class.getName());

    public void processAll(Island island) {
    island.forEachCell((x, y) -> {
        try {
            Cell cell = island.getCell(x, y);
            process(cell, x, y);
        } catch (Exception e) {
            LOGGER.warning(String.format(
                "Ошибка при обработке клетки (%d, %d): %s", x, y, e.getMessage()
            ));
            // Логируем стек вызовов только если это серьёзная ошибка
            if (e instanceof RuntimeException && !(e instanceof IllegalArgumentException)) {
                LOGGER.log(java.util.logging.Level.SEVERE, "Стек вызовов:", e);
            }
        }
    });
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
        Plant plant = cell.getPlants();
        for (Animals herbivore : animals) {
            if (herbivore instanceof Herbivores && cell.hasPlantsAvailable()) {
                ((Herbivores) herbivore).setCurrentCell(cell);
                boolean ate = herbivore.eat();
                if (ate) {
                    Plant updated = plant.consume();
                    cell.setPlants(updated);
                }
            }
        }

        // --- Рост растений ---
        if (plant.canGrow() && GameUtils.RANDOM.nextDouble() < PlantConfig.getGrowthRate()) {
            Plant updated = (Plant) plant.createNewInstance();
            cell.setPlants(updated);
        }

        // --- Размножение ---
        for (Animals animal : new ArrayList<>(cell.getAnimals())) {
            Organism child = animal.reproduce(cell);
            if (child != null) {
                cell.addAnimal((Animals) child);
            }
        }

        // --- Конец дня для всех животных ---
        for (Animals animal : animals) {
            if (animal instanceof Predators p) {
                p.endOfDay();
            }
            if (animal instanceof Herbivores h) {
                h.endOfDay();
            }
        }

        // --- Голодание ---
        for (Animals animal : animals) {
            animal.starve();
        }

        // --- Удаление мёртвых + лог ---
        animals.removeIf(animal -> !animal.isAlive());
        for (Animals dead : animals) {
            if (!dead.isAlive()) {
                LOGGER.info(TranslationUtil.toNominativ(dead.getType()) +
                        " умер(ла) от голода в клетке (" + x + ", " + y + ")");
            }
            cell.getAnimals().remove(dead);
        }
    }
}