package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Duck extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Duck.class.getName());

    public Duck(int currentCount) {
        super("duck", currentCount);
    }

    @Override
    protected Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился утёнок!");
        return new Duck(1);
    }

    @Override
    public String toString() {
        return "Duck{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
