package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.PlantConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Plant extends Organism {

    private static final Logger LOGGER = Logger.getLogger(Plant.class.getName());

    private final int currentCount;
    private static final int MAX_PLANTS_PER_CELL = PlantConfig.getMaxCount();

    public Plant(int count) {
        super("plant");
        this.currentCount = Math.max(count, 0);
    }

    public boolean eat() {
        LOGGER.log(Level.WARNING, "Растения не едят.");
        return false;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    @Override
    public Organism createNewInstance() {
        if (currentCount >= MAX_PLANTS_PER_CELL) {
            return new Plant(currentCount); // копия, а не this
        }
        return new Plant(currentCount + 1);
    }

    public Plant consume() {
        if (currentCount <= 0) {
            LOGGER.log(Level.WARNING, "Нечего есть — растений нет.");
            return this;
        }
        return new Plant(currentCount - 1);
    }

    public boolean canGrow() {
        return currentCount > 0 && currentCount < MAX_PLANTS_PER_CELL;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "count=" + currentCount +
                ", max=" + MAX_PLANTS_PER_CELL +
                '}';
    }
}
