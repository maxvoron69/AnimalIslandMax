package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sheep extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Sheep.class.getName());

    public Sheep() {
        super("sheep");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился ягнёнок!");
        return new Sheep();
    }

    @Override
    public String toString() {
        return "Sheep{" +
                ", weight=" + getWeight() +
                '}';
    }
}
