package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.config.PlantConfig;
import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Herbivores extends Animals {
    private static final Logger LOGGER = Logger.getLogger(Herbivores.class.getName());

    protected Cell currentCell;
    private double foodEatenToday = 0.0;
    private int daysWithoutFullMeal = 0;
    private static final double EATING_SUCCESS_RATE = AnimalConfig.getHerbivoreBaseEatingSuccessRate();


    public Herbivores(String type) {
        super(type);
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    @Override
    public boolean eat() {
        if (currentCell == null || !currentCell.hasPlantsAvailable()) {
            LOGGER.log(Level.INFO, getLocalizedType() + " не нашёл(а) растений рядом.");
            checkFullMeal();
            return false;
        }

        Plant plant = currentCell.getPlants();
        double plantWeight = PlantConfig.getPlantWeight();
        double neededToFull = maximumFoodLoad - foodEatenToday;
        double canEatNow = Math.min(plantWeight, neededToFull);

        if (canEatNow <= 0) {
            LOGGER.log(Level.INFO, getLocalizedType() + " уже сыт(а).");
            checkFullMeal();
            return true;
        }

        if (GameUtils.RANDOM.nextDouble() < EATING_SUCCESS_RATE) {
            Plant updated = plant.consume();
            currentCell.setPlants(updated);

            foodEatenToday += canEatNow;

            LOGGER.log(Level.INFO, String.format(
                    "%s съел(а) %.2f кг растений. Всего сегодня: %.2f кг",
                    getLocalizedType(), canEatNow, foodEatenToday
            ));

            if (foodEatenToday >= maximumFoodLoad) {
                resetHunger();
                LOGGER.log(Level.INFO, getLocalizedType() + " полностью насытился(лась)!");
                daysWithoutFullMeal = 0;
            }
            return true;
        } else {
            LOGGER.log(Level.INFO, getLocalizedType() + " попытался(ась) поесть, но не смог(ла).");
            checkFullMeal();
            return false;
        }
    }

    public void endOfDay() {
        checkFullMeal();
        foodEatenToday = 0.0;
    }

    private void checkFullMeal() {
        if (foodEatenToday < maximumFoodLoad) {
            daysWithoutFullMeal++;
            LOGGER.log(Level.INFO, String.format(
                    "%s не наелся(лась) досыта. Дней без полного приёма пищи: %d",
                    getLocalizedType(), daysWithoutFullMeal
            ));
        } else {
            daysWithoutFullMeal = 0;
        }

        if (daysWithoutFullMeal >= 5) {
            this.daysWithoutFood = MAX_DAYS_WITHOUT_FOOD;
            LOGGER.log(Level.WARNING, getLocalizedType() + " умер(ла) от голода (не наедался 5 дней подряд)");
        }
    }
}