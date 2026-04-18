package ru.javarush.pastukhov.animalisland.entity;

public class Rabbit extends Herbivores {

    public Rabbit(int currentCount) {
        super("rabbit", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Кролик щиплет травку...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Rabbit(1);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
