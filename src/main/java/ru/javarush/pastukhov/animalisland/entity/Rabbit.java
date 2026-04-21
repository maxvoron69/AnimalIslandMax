package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Rabbit extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Rabbit.class.getName());

    public Rabbit(int currentCount) {
        super("rabbit", currentCount);
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родился крольчонок!");
        return new Rabbit(1);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
