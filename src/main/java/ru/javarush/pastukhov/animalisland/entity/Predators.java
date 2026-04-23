package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.PredatorFoodConfig;
import ru.javarush.pastukhov.animalisland.util.GameUtils;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public abstract class Predators extends Animals {

    private static final Logger LOGGER = Logger.getLogger(Predators.class.getName());

    public Predators(String type, int currentCount) {
        super(type, currentCount);
    }

    public boolean hunt(String preyType, Cell cell) {
        double successRate = PredatorFoodConfig.getSuccessRate(this.type, preyType);

        if (successRate <= 0) {
            String message = getLocalizedType() + " не охотится на " + TranslationUtil.toRussian(preyType);
            LOGGER.log(Level.INFO, message);
            return false;
        }

        List<Animals> animals = cell.getAnimals();
        boolean preyFound = false;

        for (Animals animal : new ArrayList<>(animals)) {
            if (animal.getType().equals(preyType) && animal != this) {
                preyFound = true;
                if (GameUtils.RANDOM.nextDouble() < successRate) {
                    animals.remove(animal);
                    resetHunger();
                    String message = String.format("%s успешно съел %s (%.0f%% шанс) в клетке (%d, %d)",
                            getLocalizedType(),
                            TranslationUtil.toRussian(preyType),
                            successRate * 100,
                            cell.getX(),
                            cell.getY());
                    LOGGER.log(Level.INFO, message);
                    return true; // ✅ Успех — выходим
                } else {
                    String message = String.format("%s промахнулся при охоте на %s",
                            getLocalizedType(),
                            TranslationUtil.toRussian(preyType));
                    LOGGER.log(Level.INFO, message);
                }
            }
        }

        if (!preyFound) {
            String message = String.format("%s не нашёл %s для охоты",
                    getLocalizedType(),
                    TranslationUtil.toNominativPlural(preyType));
            LOGGER.log(Level.INFO, message);
        }

        return false;
    }

    @Override
    public final boolean eat() {
        LOGGER.log(Level.WARNING, "Хищник должен указать на кого охотится.");
        return false;
    }
}
