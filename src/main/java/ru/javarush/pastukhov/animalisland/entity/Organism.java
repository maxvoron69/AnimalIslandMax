package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.config.GameConfig;
import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

import static ru.javarush.pastukhov.animalisland.config.AnimalConfig.getMaxCount;

public abstract class Organism {
    protected String type;
    protected int currentCount;

    public Organism(String type, int currentCount) {
        this.type = type;
        this.currentCount = currentCount;
    }

    public Organism reproduce() {
        if (currentCount >= AnimalConfig.getMaxCount(type)) {
            return null;
        }
        return createNewInstance();
    }

    public abstract Organism createNewInstance();

    public String getType() {
        return type;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public String getLocalizedType() {
        return TranslationUtil.capitalize(TranslationUtil.toNominativ(getType()));
    }
}

