package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.PlantConfig;
import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Plant extends Organism {

    private static final Logger LOGGER = Logger.getLogger(Plant.class.getName());

    // Максимальное количество растений на одной клетке
    private static final int MAX_PLANTS_PER_CELL = PlantConfig.getMaxCount();

    // Вес одного растения (сколько еды даёт)
    private static final double PLANT_WEIGHT = PlantConfig.getWeight();

    public Plant(int count) {
        super("plant", Math.min(count, MAX_PLANTS_PER_CELL));
    }

    public boolean eat() {
        LOGGER.log(Level.WARNING, "Растения не едят.");
        return false;
    }

    @Override
    public Organism createNewInstance() {
        if (getCurrentCount() >= MAX_PLANTS_PER_CELL) {
            return new Plant(getCurrentCount()); // уже максимум
        }

        if (GameUtils.RANDOM.nextDouble() < PlantConfig.getGrowthRate()) {
            int newCount = Math.min(getCurrentCount() + 1, MAX_PLANTS_PER_CELL);
            return new Plant(newCount);
        }

        return new Plant(getCurrentCount());
    }

    public Plant consume() {
        if (getCurrentCount() <= 0) {
            LOGGER.log(Level.WARNING, "Нечего есть — растений нет.");
            return this;
        }

        int newCount = getCurrentCount() - 1;
        LOGGER.log(Level.INFO, "Травоядное съело одно растение. Осталось: " + newCount);
        return new Plant(newCount);
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
