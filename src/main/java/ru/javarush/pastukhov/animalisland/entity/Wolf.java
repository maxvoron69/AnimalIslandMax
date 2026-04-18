package ru.javarush.pastukhov.animalisland.entity;

public class Wolf extends Predators {

    public Wolf(int currentCount) {
        super("wolf", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Волк ест добычу...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Wolf(1);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
