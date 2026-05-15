package ru.javarush.pastukhov.animalisland.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Wolf extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Wolf.class.getName());

    public Wolf() {
        super("wolf");
    }

    @Override
    public Organism createNewInstance(int currentTurn) {
        Wolf wolf = new Wolf();
        wolf.setPosition(this.getX(), this.getY());
        wolf.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился волчонок!");
        return wolf;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                ", weight=" + getWeight() +
                '}';
    }
}
