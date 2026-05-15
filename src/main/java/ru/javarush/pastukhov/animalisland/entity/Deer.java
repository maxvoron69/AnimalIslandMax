package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Deer extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Deer.class.getName());

    public Deer() {
        super("deer");
    }

    @Override
    public Organism createNewInstance(int currentTurn) {
        Deer deer = new Deer();
        deer.setPosition(this.getX(), this.getY());
        deer.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился оленёнок!");
        return deer;
    }

    @Override
    public String toString() {
        return "Deer{" +
                ", weight=" + getWeight() +
                '}';
    }
}
