package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.GameConfig;

import static ru.javarush.pastukhov.animalisland.config.AnimalConfig.getMaxCount;

public abstract class Organism {
    protected String type;

    protected int currentCount;

    public Organism(String type, int currentCount) {
        this.type = type;
        this.currentCount = currentCount;
    }

    public Organism reproduce() {
        int maxCount = getMaxCount(type);
        int reproductionCount = GameConfig.getReproductionCount(type);

        if (currentCount >= maxCount) {
            return null;
        } else {
            currentCount++;
            return createNewInstance();
        }
    }
    protected abstract Organism createNewInstance();

    public String getType() {
        return type;
    }

    public int getCurrentCount() {
        return currentCount;
    }
}

