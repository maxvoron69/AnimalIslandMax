package ru.javarush.pastukhov.animalisland.entity.predators;

import ru.javarush.pastukhov.animalisland.entity.Organism;
import ru.javarush.pastukhov.animalisland.entity.Predators;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Fox extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Fox.class.getName());

    public Fox() {
        super("fox");
    }

    @Override
    public Organism createNewInstance(int currentTurn) {
        Fox fox = new Fox();
        fox.setPosition(this.getX(), this.getY());
        fox.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился лисёнок!");
        return fox;
    }

    @Override
    public String toString() {
        return "Fox{" +
                ", weight=" + getWeight() +
                '}';
    }
}
