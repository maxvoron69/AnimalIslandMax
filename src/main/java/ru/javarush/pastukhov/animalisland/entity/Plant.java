package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.PlantConfig;
import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Plant extends Organism {

    private static final Logger LOGGER = Logger.getLogger(Plant.class.getName());

    // Максимальное количество растений на одной клетке
    private static final int MAX_PLANTS_PER_CELL = PlantConfig.getMaxCountPlant();

    // Вес одного растения (сколько еды даёт)
    private static final double PLANT_WEIGHT = PlantConfig.getWeightPlant();

    public Plant(int count) {
        super("plant", Math.min(count, MAX_PLANTS_PER_CELL));
    }

    @Override
    public boolean eat() {
        LOGGER.log(Level.WARNING, "Растения не едят.");
        return false;
    }

    @Override
    protected Organism createNewInstance() {
        if (getCurrentCount() >= MAX_PLANTS_PER_CELL) {
            return this; // уже максимум
        }

        if (GameUtils.RANDOM.nextDouble() < PlantConfig.getGrowthRate()) {
            int newCount = Math.min(getCurrentCount() + 1, MAX_PLANTS_PER_CELL);
            LOGGER.log(Level.INFO, "Растения размножились! Теперь: " + newCount + " шт.");
            return new Plant(newCount);
        }

        return this;
    }

    public void consume() {
        if (getCurrentCount() > 0) {
            setCurrentCount(getCurrentCount() - 1);
            LOGGER.log(Level.INFO, "Травоядное съело одно растение. Осталось: " + getCurrentCount());
        }
    }

    public double getPlantWeight() {
        return PLANT_WEIGHT;
    }

    public boolean canGrow() {
        return getCurrentCount() < MAX_PLANTS_PER_CELL;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "count=" + getCurrentCount() +
                ", max=" + MAX_PLANTS_PER_CELL +
                '}';
    }
}
