package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Wolf extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Wolf.class.getName());

    public Wolf(int currentCount) {
        super("wolf", currentCount);
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился волчонок!");
        return new Wolf(1);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
