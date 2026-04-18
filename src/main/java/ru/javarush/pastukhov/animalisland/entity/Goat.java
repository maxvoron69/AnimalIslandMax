package ru.javarush.pastukhov.animalisland.entity;

public class Goat extends Herbivores{

    public Goat(int currentCount) {
        super("goat", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Коза щиплет травку...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Goat(1);
    }

    @Override
    public String toString() {
        return "Goat{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
