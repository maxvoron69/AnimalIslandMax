package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sheep extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Sheep.class.getName());

    public Sheep() {
        super("sheep");
    }

    @Override
    public Organism createNewInstance(int currentTurn) {
        Sheep sheep = new Sheep();
        sheep.setPosition(this.getX(), this.getY());
        sheep.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился ягнёнок!");
        return sheep;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                ", weight=" + getWeight() +
                '}';
    }
}
