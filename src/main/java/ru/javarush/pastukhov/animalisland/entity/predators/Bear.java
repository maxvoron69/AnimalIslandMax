package ru.javarush.pastukhov.animalisland.entity.predators;

import ru.javarush.pastukhov.animalisland.entity.Organism;
import ru.javarush.pastukhov.animalisland.entity.Predators;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bear extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Bear.class.getName());

    public Bear() {
        super("bear");
    }

    @Override
     public Organism createNewInstance(int currentTurn) {
        Bear bear = new Bear();
        bear.setPosition(this.getX(), this.getY());
        bear.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился медвежонок!");
        return bear;
    }

    @Override
    public String toString() {
        return "Bear{" +
                ", weight=" + getWeight() +
                '}';
    }
}