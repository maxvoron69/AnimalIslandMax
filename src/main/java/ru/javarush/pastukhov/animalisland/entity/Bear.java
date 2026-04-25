package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bear extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Bear.class.getName());

    public Bear() {
        super("bear");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился медвежонок!");
        return new Bear();
    }

    @Override
    public String toString() {
        return "Bear{" +
                ", weight=" + getWeight() +
                '}';
    }
}