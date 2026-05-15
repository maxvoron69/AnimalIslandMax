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
    public Organism createNewInstance(int currentTurn) {
        Rabbit rabbit = new Rabbit();
        rabbit.setPosition(this.getX(), this.getY());
        rabbit.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился крольчонок!");
        return rabbit;
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                ", weight=" + getWeight() +
                '}';
    }
}
