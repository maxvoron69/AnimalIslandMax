package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sheep extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Sheep.class.getName());

    public Sheep(int currentCount) {
        super("sheep", currentCount);
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился ягнёнок!");
        return new Sheep(1);
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
