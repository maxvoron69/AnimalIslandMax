package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mouse extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Mouse.class.getName());

    public Mouse() {
        super("mouse");
    }

    @Override
    public Organism createNewInstance() {
        Mouse mouse = new Mouse();
        mouse.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился мышонок!");
        return mouse;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                " weight=" + getWeight() +
                '}';
    }
}
