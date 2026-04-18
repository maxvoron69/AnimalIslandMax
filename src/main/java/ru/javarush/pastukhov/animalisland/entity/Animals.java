package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;

import java.util.Random;

public abstract class Animals extends Organism {

    protected double weight;
    protected int speed;
    protected double maximumFoodLoad;
    protected int maxCount;

    private static final Random RANDOM = new Random();

    public Animals(String type, int currentCount) {
        super(type, currentCount);
    this.weight= AnimalConfig.getWeight(type);
    this.speed=AnimalConfig.getSpeed(type);
    this.maximumFoodLoad=AnimalConfig.getMaximumFoodLoad(type);
    }
    public abstract boolean eat();

    public String chooseDirection() {
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT", "NONE"};
        int index = RANDOM.nextInt(directions.length);
        return directions[index];
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

    public double getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMaximumFoodLoad() {
        return maximumFoodLoad;
    }
}


