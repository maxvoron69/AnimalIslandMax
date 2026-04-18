package ru.javarush.pastukhov.animalisland.entity;

public class Bear extends Predators {


    public Bear(int currentCount) {
        super("bear", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Медведь ест добычу...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Bear(1);
    }

    @Override
    public String toString() {
        return "Bear{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}