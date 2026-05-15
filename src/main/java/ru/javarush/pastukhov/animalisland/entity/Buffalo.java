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
    public Organism createNewInstance(int currentTurn) {
        Buffalo buffalo = new Buffalo();
        buffalo.setPosition(this.getX(), this.getY());
        buffalo.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился буйволёнок!");
        return buffalo;
    }

    @Override
    public String toString() {
        return "Buffalo{" +
                ", weight=" + getWeight() +
                '}';
    }
}
