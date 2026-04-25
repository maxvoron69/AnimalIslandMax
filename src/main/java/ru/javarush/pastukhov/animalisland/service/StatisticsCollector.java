package ru.javarush.pastukhov.animalisland.service;

import ru.javarush.pastukhov.animalisland.entity.Animals;
import ru.javarush.pastukhov.animalisland.entity.Cell;
import ru.javarush.pastukhov.animalisland.entity.Island;
import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.model.PopulationRecord;

import java.util.*;

public class StatisticsCollector {
    public PopulationRecord collect(Island island, int turn) {
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
                    counts.merge(type, 1, Integer::sum);
                }
                totalPlants += cell.getPlants().getCurrentCount();
            }
        }

        return new PopulationRecord(
                turn,
                new HashMap<>(counts),
                totalPlants);
    }
}