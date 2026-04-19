package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Herbivores extends Animals {

    private static final Logger LOGGER = Logger.getLogger(Herbivores.class.getName());
    protected static final double EATING_SUCCESS_RATE = AnimalConfig.getHerbivoreBaseEatingSuccessRate();

    public Herbivores(String type, int currentCount) {
        super(type, currentCount);
    }

    @Override
    public boolean eat() {
        if (GameUtils.RANDOM.nextDouble() < EATING_SUCCESS_RATE) {
            String message = getLocalizedType() + " успешно поел!";
            LOGGER.log(Level.INFO, message);
            return true;
        } else {
            String message = getLocalizedType() + " не смог найти еду.";
            LOGGER.log(Level.INFO, message);
            return false;
        }
    }
}
