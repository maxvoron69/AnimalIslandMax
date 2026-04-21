package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Eagle extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Eagle.class.getName());

    public Eagle(int currentCount) {
        super("eagle", currentCount);
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился орлёнок!");
        return new Eagle(1);
    }

    @Override
    public String toString() {
        return "Eagle{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
