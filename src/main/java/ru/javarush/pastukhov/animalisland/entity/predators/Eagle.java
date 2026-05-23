package ru.javarush.pastukhov.animalisland.entity.predators;

import ru.javarush.pastukhov.animalisland.entity.Organism;
import ru.javarush.pastukhov.animalisland.entity.Predators;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Eagle extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Eagle.class.getName());

    public Eagle() {
        super("eagle");
    }

    @Override
    public Organism createNewInstance() {
        Eagle eagle = new Eagle();
        eagle.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился орлёнок!");
        return eagle;
    }

    @Override
    public String toString() {
        return "Eagle{" +
                " weight=" + getWeight() +
                '}';
    }
}
