package ru.javarush.pastukhov.animalisland.entity;

public class Boa extends Predators{

    public Boa(int currentCount) {
        super("boa", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Удав заглотил добычу...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Boa(1);
    }

    @Override
    public String toString() {
        return "Boa{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
