package ru.javarush.pastukhov.animalisland.model;

import java.util.Map;

public record PopulationRecord(int turn, Map<String, Integer> animalCounts, int plants) {

    @Override
    public String toString() {
        return String.format("Ход %d: %s, растения=%d",
            turn,
            animalCounts.toString(),
            plants);
    }
}

