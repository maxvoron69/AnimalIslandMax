package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Horse extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Horse.class.getName());

    public Horse() {
        super("horse");
    }

    @Override
    public Organism createNewInstance(int currentTurn) {
        Horse horse = new Horse();
        horse.setPosition(this.getX(), this.getY());
        horse.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился жеребёнок!");
        return horse;
    }

    @Override
    public String toString() {
        return "Horse{" +
                ", weight=" + getWeight() +
                '}';
    }
}
