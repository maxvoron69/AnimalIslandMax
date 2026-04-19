package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bear extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Bear.class.getName());

    public Bear(int currentCount) {
        super("bear", currentCount);
    }

    @Override
    protected Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился медвежонок!");
        return new Bear(1);
    }

    @Override
    public String toString() {
        return "Bear{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}