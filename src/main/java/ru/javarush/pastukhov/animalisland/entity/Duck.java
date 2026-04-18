package ru.javarush.pastukhov.animalisland.entity;

public class Duck extends Herbivores{

    public Duck(int currentCount) {
        super("duck", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Утка ест гусеницу...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Duck(1);
    }

    @Override
    public String toString() {
        return "Duck{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
