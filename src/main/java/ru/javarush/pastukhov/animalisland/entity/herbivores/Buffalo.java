package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffalo extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Buffalo.class.getName());

    public Buffalo() {
        super("buffalo");
    }

    @Override
    public Organism createNewInstance() {
        Buffalo buffalo = new Buffalo();
        buffalo.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился буйволёнок!");
        return buffalo;
    }

    @Override
    public String toString() {
        return "Buffalo{" +
                " weight=" + getWeight() +
                '}';
    }
}
