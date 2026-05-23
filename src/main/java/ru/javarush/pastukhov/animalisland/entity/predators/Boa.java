package ru.javarush.pastukhov.animalisland.entity.predators;

import ru.javarush.pastukhov.animalisland.entity.Organism;
import ru.javarush.pastukhov.animalisland.entity.Predators;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Boa extends Predators {

    private static final Logger LOGGER = Logger.getLogger(Boa.class.getName());

    public Boa() {
        super("boa");
    }

    @Override
    public Organism createNewInstance() {
        Boa boa = new Boa();
        boa.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился удав!");
        return boa;
    }

    @Override
    public String toString() {
        return "Boa{" +
                " weight=" + getWeight() +
                '}';
    }
}

