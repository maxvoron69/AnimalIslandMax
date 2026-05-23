package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Goat extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Goat.class.getName());

    public Goat() {
        super("goat");
    }

    @Override
    public Organism createNewInstance() {
        Goat goat = new Goat();
        goat.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился козлёнок!");
        return goat;
    }

    @Override
    public String toString() {
        return "Goat{" +
                " weight=" + getWeight() +
                '}';
    }
}
