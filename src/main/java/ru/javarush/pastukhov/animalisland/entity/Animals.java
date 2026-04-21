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

    public void moveTo(Cell fromCell, Cell toCell) {
        if (fromCell != null && toCell != null) {
            fromCell.getAnimals().remove(this);
            toCell.addAnimal(this);
        }
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
        int maxCount = AnimalConfig.getMaxCount(getType());
        if (getCurrentCount() >= maxCount) {
            return null;
        }
        Organism child = super.reproduce();
        if (child != null) {
            System.out.println("Родился " + getType() + "!");
        }
        return child;
    }
}

