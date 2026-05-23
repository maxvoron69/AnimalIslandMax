package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Deer extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Deer.class.getName());

    public Deer() {
        super("deer");
    }

    @Override
    public Organism createNewInstance() {
        Deer deer = new Deer();
        deer.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился оленёнок!");
        return deer;
    }

    @Override
    public String toString() {
        return "Deer{" +
                " weight=" + getWeight() +
                '}';
    }
}
