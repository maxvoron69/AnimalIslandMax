package ru.javarush.pastukhov.animalisland.entity;

public class Sheep extends Herbivores {

    public Sheep(int currentCount) {
        super("sheep", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Овца щиплет травку...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Sheep(1);
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
