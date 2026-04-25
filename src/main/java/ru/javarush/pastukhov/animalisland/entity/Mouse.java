package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mouse extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Mouse.class.getName());

    public Mouse() {
        super("mouse");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился мышонок!");
        return new Mouse();
    }

    @Override
    public String toString() {
        return "Mouse{" +
                ", weight=" + getWeight() +
                '}';
    }
}
