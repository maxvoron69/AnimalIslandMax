package ru.javarush.pastukhov.animalisland.entity;

public class Horse extends Herbivores{

    public Horse(int currentCount) {
        super("horse", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Лошадь щиплет траву...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Horse(1);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
