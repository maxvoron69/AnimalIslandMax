package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffalo extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Buffalo.class.getName());

    public Buffalo() {
        super("buffalo");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился буйволёнок!");
        return new Buffalo();
    }

    @Override
    public String toString() {
        return "Buffalo{" +
                ", weight=" + getWeight() +
                '}';
    }
}
