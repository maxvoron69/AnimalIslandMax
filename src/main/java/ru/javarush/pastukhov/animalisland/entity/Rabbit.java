package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Rabbit extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Rabbit.class.getName());

    public Rabbit() {
        super("rabbit");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился крольчонок!");
        return new Rabbit();
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                ", weight=" + getWeight() +
                '}';
    }
}
