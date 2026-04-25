package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.util.Direction;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.logging.Logger;

public abstract class Animals extends Organism implements Movable {

    protected double weight;
    protected int speed;
    protected double maximumFoodLoad;
    protected int maxCount;
    private int x, y;

    protected int daysWithoutFood = 0;
    protected static final int MAX_DAYS_WITHOUT_FOOD = 3;

    protected int daysWithoutFullMeal = 0;
    protected static final int MAX_DAYS_WITHOUT_FULL_MEAL = 5;

    private static final Logger LOGGER = Logger.getLogger(Animals.class.getName());

    public Animals(String type) {
        super(type);
        this.weight = AnimalConfig.getWeight(type);
        this.speed = AnimalConfig.getSpeed(type);
        this.maximumFoodLoad = AnimalConfig.getMaximumFoodLoad(type);
        this.maxCount = AnimalConfig.getMaxCount(type);
    }

    public abstract boolean eat();

    public Direction chooseDirection() {
        return Direction.getRandom();
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int[] getPosition() {
        return new int[]{x, y};
    }

    public Animals reproduce(Cell cell) {
        int totalInCell = getTotalCountInCell(cell);
        if (totalInCell >= maxCount) {
            return null;
        }
        Animals child = (Animals) createNewInstance();
        child.setPosition(this.x, this.y);
        return child;
    }

    public void starve() {
        daysWithoutFood++;
        if (daysWithoutFood >= MAX_DAYS_WITHOUT_FOOD) {
            LOGGER.warning(TranslationUtil.toNominativ(this.type) + " умер(ла) от полного голода");
        }
    }

    public void resetHunger() {
        this.daysWithoutFood = 0;
    }

    public void checkFullMeal(double foodEatenToday) {
        if (foodEatenToday < maximumFoodLoad) {
            daysWithoutFullMeal++;
            if (daysWithoutFullMeal >= MAX_DAYS_WITHOUT_FULL_MEAL) {
                LOGGER.warning(TranslationUtil.toNominativ(this.type) +
                        " умер(ла) от хронического недоедания (5 дней без полного насыщения)");
            }
        } else {
            daysWithoutFullMeal = 0; // сыт — сбрасываем
        }
    }

    public boolean isAlive() {
        return daysWithoutFood < MAX_DAYS_WITHOUT_FOOD &&
                daysWithoutFullMeal < MAX_DAYS_WITHOUT_FULL_MEAL;
    }

    private int getTotalCountInCell(Cell cell) {
        return (int) cell.getAnimals().stream()
                .filter(animal -> animal.getType().equals(getType()))
                .count();
    }

    public double getWeight() {
        return weight;
    }

}