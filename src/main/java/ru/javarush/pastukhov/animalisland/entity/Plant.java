package ru.javarush.pastukhov.animalisland.entity;

public class Plant extends Organism {
    public Plant(int count) {
        super("plant", count);
    }

    @Override
    protected Organism createNewInstance() {
        return new Plant(getCurrentCount());
    }

    @Override
    public String toString() {
        return "Plant{" +
                "currentCount=" + getCurrentCount() +
                '}';
    }
}

