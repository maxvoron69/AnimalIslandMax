package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Boa extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Boa.class.getName());

    public Boa(int currentCount) {
        super("boa", currentCount);
    }

    @Override
    protected Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился удав!");
        return new Boa(1);
    }

    @Override
    public String toString() {
        return "Boa{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}

