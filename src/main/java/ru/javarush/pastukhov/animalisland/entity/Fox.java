package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Fox extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Fox.class.getName());

    public Fox() {
        super("fox");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился лисёнок!");
        return new Fox();
    }

    @Override
    public String toString() {
        return "Fox{" +
                ", weight=" + getWeight() +
                '}';
    }
}
