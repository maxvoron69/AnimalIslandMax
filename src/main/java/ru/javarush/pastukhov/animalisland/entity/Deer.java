package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Deer extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Deer.class.getName());

    public Deer() {
        super("deer");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился оленёнок!");
        return new Deer();
    }

    @Override
    public String toString() {
        return "Deer{" +
                ", weight=" + getWeight() +
                '}';
    }
}
