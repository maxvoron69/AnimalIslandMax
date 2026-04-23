package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.util.Direction;

public abstract class Animals extends Organism {

    protected double weight;
    protected int speed;
    protected double maximumFoodLoad;
    protected int maxCount;

    private int daysWithoutFood = 0;
    private static final int MAX_DAYS_WITHOUT_FOOD = 5;

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

    public Organism reproduce(Cell cell) {
        int totalInCell = getTotalCountInCell(cell);
        if (totalInCell >= maxCount) {
            return null;
        }
        Organism child = createNewInstance();
        return child;
    }

    public void starve() {
        daysWithoutFood++;
    }

    public void resetHunger() {
        this.daysWithoutFood = 0;
    }

    public boolean isAlive() {
        return daysWithoutFood < MAX_DAYS_WITHOUT_FOOD;
    }

    public int getDaysWithoutFood() {
        return daysWithoutFood;
    }


    private int getTotalCountInCell(Cell cell) {
        return (int) cell.getAnimals().stream()
                .filter(animal -> animal.getType().equals(getType()))
                .count();
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
}

