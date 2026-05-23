package ru.javarush.pastukhov.animalisland.entity.predators;

import ru.javarush.pastukhov.animalisland.entity.Organism;
import ru.javarush.pastukhov.animalisland.entity.Predators;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Wolf extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Wolf.class.getName());

    public Wolf() {
        super("wolf");
    }

    @Override
    public Organism createNewInstance() {
        Wolf wolf = new Wolf();
        wolf.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился волчонок!");
        return wolf;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                " weight=" + getWeight() +
                '}';
    }
}
