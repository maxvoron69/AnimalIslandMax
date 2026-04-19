package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.PredatorFoodConfig;
import ru.javarush.pastukhov.animalisland.util.GameUtils;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import java.util.logging.Logger;
import java.util.logging.Level;

public abstract class Predators extends Animals {

    private static final Logger LOGGER = Logger.getLogger(Predators.class.getName());

    public Predators(String type, int currentCount) {
        super(type, currentCount);
    }

    public boolean eat(String preyType) {
        double successRate = PredatorFoodConfig.getSuccessRate(this.type, preyType);

        if (successRate <= 0) {
            String message = getLocalizedType() + " не охотится на " + TranslationUtil.toRussian(preyType);
            LOGGER.log(Level.INFO, message);
            return false;
        }

        if (GameUtils.RANDOM.nextDouble() < successRate) {
            String message = String.format("%s успешно съел %s (%.0f%% шанс)",
                    getLocalizedType(),
                    TranslationUtil.toRussian(preyType),
                    successRate * 100);
            LOGGER.log(Level.INFO, message);
            return true;
        } else {
            String message = String.format("%s промахнулся при охоте на %s",
                    getLocalizedType(),
                    TranslationUtil.toRussian(preyType));
            LOGGER.log(Level.INFO, message);
            return false;
        }
    }

    @Override
    public final boolean eat() {
        LOGGER.log(Level.WARNING, "Хищник должен указать на кого охотится.");
        return false;
    }
}
