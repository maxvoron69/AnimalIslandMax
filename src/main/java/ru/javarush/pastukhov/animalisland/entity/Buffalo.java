package ru.javarush.pastukhov.animalisland.entity;

public class Buffalo extends Herbivores{

    public Buffalo(int currentCount) {
        super("buffalo", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Буйвол щиплет травку...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new  Buffalo(1);
    }
    @Override
    public String toString() {
        return "Buffalo{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
