package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.config.PredatorFoodConfig;
import ru.javarush.pastukhov.animalisland.util.GameUtils;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Predators extends Animals {
    private static final Logger LOGGER = Logger.getLogger(Predators.class.getName());

    private double foodEatenToday = 0.0;
    private int daysWithoutFullMeal = 0;

    public Predators(String type) {
        super(type);
    }

    public boolean hunt(String preyType, Cell cell) {
        double successRate = PredatorFoodConfig.getSuccessRate(this.type, preyType);

        if (successRate <= 0) {
            String message = getLocalizedType() + " не охотится на " + TranslationUtil.toRussian(preyType);
            LOGGER.log(Level.INFO, message);
            return false;
        }

        List<Animals> animals = new ArrayList<>(cell.getAnimals());
        boolean preyFound = false;
        boolean huntSuccessful = false;

        for (Animals animal : animals) {
            if (animal.getType().equals(preyType) && animal != this) {
                preyFound = true;

                if (foodEatenToday >= maximumFoodLoad) {
                    LOGGER.info(getLocalizedType() + " уже сыт(а)");
                    break;
                }

                if (GameUtils.RANDOM.nextDouble() < successRate) {
                    double preyWeight = AnimalConfig.getWeight(preyType);
                    double neededToFull = maximumFoodLoad - foodEatenToday;
                    double canEatNow = Math.min(preyWeight, neededToFull);

                    foodEatenToday += canEatNow;
                    cell.getAnimals().remove(animal);

                    LOGGER.info(String.format(
                            "%s съел(а) %.2f кг %s. Всего сегодня: %.2f кг",
                            getLocalizedType(),
                            canEatNow,
                            TranslationUtil.toGenetiv(animal.getType()),
                            foodEatenToday
                    ));

                    huntSuccessful = true;

                    if (foodEatenToday >= maximumFoodLoad) {
                        resetHunger();
                        LOGGER.log(Level.INFO, getLocalizedType() + " полностью насытился(лась)!");
                        daysWithoutFullMeal = 0;
                    }
                } else {
                    LOGGER.info(String.format("%s промахнулся при охоте на %s",
                            getLocalizedType(),
                            TranslationUtil.toRussian(preyType)));
                }
            }
        }

        if (!preyFound) {
            String message = String.format("%s не нашёл %s для охоты",
                    getLocalizedType(),
                    TranslationUtil.toNominativPlural(preyType));
            LOGGER.log(Level.INFO, message);
        }
        return huntSuccessful;
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

    @Override
    public final boolean eat() {
        LOGGER.log(Level.WARNING, "Хищник должен указать на кого охотится.");
        return false;
    }
}