package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Caterpillar extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Caterpillar.class.getName());

    public Caterpillar() {
        super("caterpillar");
    }

    @Override
    public Organism createNewInstance(int currentTurn) {
        Caterpillar caterpillar = new Caterpillar();
        caterpillar.setPosition(this.getX(), this.getY());
        caterpillar.nextAllowedReproduceTurn = currentTurn + 3;
        LOGGER.log(Level.INFO, "Родилась гусеничка!");
        return caterpillar;
    }

    @Override
    public String toString() {
        return "Caterpillar{" +
                ", weight=" + getWeight() +
                '}';
    }
}
