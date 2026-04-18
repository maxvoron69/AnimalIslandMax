package ru.javarush.pastukhov.animalisland.entity;

public class Caterpillar extends Herbivores {

    public Caterpillar(int currentCount) {
        super("caterpillar", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Гусеница ест листочки...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Caterpillar(1);
    }

    @Override
    public String toString() {
        return "Caterpillar{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
