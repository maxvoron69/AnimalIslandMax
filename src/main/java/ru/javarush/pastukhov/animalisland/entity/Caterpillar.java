package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Caterpillar extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Caterpillar.class.getName());

    public Caterpillar() {
        super("caterpillar");
    }

    @Override
    public Organism createNewInstance() {
        LOGGER.log(Level.INFO, "Родилась гусеничка!");
        return new Caterpillar();
    }

    @Override
    public String toString() {
        return "Caterpillar{" +
                ", weight=" + getWeight() +
                '}';
    }
}
