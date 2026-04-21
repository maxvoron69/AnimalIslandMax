package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Horse extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Horse.class.getName());

    public Horse(int currentCount) {
        super("horse", currentCount);
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился жеребёнок!");
        return new Horse(1);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
