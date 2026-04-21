package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffalo extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Buffalo.class.getName());

    public Buffalo(int currentCount) {
        super("buffalo", currentCount);
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился буйволёнок!");
        return new Buffalo(1);
    }

    @Override
    public String toString() {
        return "Buffalo{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
