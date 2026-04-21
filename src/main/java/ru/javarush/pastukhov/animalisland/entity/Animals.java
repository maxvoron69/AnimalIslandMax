package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.util.Direction;

public abstract class Animals extends Organism {

    protected double weight;
    protected int speed;
    protected double maximumFoodLoad;
    protected int maxCount;

    public Animals(String type, int currentCount) {
        super(type, currentCount);
        this.weight = AnimalConfig.getWeight(type);
        this.speed = AnimalConfig.getSpeed(type);
        this.maximumFoodLoad = AnimalConfig.getMaximumFoodLoad(type);
        this.maxCount = AnimalConfig.getMaxCount(type);
    }

    public abstract boolean eat();

    public Direction chooseDirection() {
        return Direction.getRandom();
    }

    public double getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMaximumFoodLoad() {
        return maximumFoodLoad;
    }

    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public Organism reproduce() {
        if (getCurrentCount() >= maxCount) {
            return null;
        }
        Organism child = createNewInstance();
        return child;
    }
}

