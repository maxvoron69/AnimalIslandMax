package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Boa extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Boa.class.getName());

    public Boa() {
        super("boa");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился удав!");
        return new Boa();
    }

    @Override
    public String toString() {
        return "Boa{" +
                ", weight=" + getWeight() +
                '}';
    }
}

