package ru.javarush.pastukhov.animalisland.entity;

public class Deer extends Herbivores{

    public Deer(int currentCount) {
        super("deer", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Олень щиплет травку...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Deer(1);
    }

    @Override
    public String toString() {
        return "Deer{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
