package ru.javarush.pastukhov.animalisland.entity;

public class Fox extends Predators{

    public Fox(int currentCount) {
        super("fox", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Лиса ест добычу...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Fox(1);
    }

    @Override
    public String toString() {
        return "Fox{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
