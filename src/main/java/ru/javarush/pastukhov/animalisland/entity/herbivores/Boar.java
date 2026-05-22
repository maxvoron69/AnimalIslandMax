package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Boar extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Boar.class.getName());

    public Boar() {
        super("boar");
    }

    @Override
    public Organism createNewInstance(int currentTurn) {
        Boar boar = new Boar();
        boar.setPosition(this.getX(), this.getY());
        boar.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родился кабанчик!");
        return boar;
    }

    @Override
    public String toString() {
        return "Boar{" +
                ", weight=" + getWeight() +
                '}';
    }
}
