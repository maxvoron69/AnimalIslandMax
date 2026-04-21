package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Goat extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Goat.class.getName());

    public Goat(int currentCount) {
        super("goat", currentCount);
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился козлёнок!");
        return new Goat(1);
    }

    @Override
    public String toString() {
        return "Goat{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
