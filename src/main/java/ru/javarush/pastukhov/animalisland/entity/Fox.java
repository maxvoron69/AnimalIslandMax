package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Fox extends Predators{

    private static final Logger LOGGER = Logger.getLogger(Fox.class.getName());

    public Fox(int currentCount) {
        super("fox", currentCount);
    }

    @Override
    protected Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился лисёнок!");
        return new Fox(1);
    }

    @Override
    public String toString() {
        return "Fox{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
