package ru.javarush.pastukhov.animalisland.entity;

public class Eagle extends Predators{

    public Eagle(int currentCount) {
        super("eagle", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Орёл ест добычу...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new  Eagle(1);
    }

    @Override
    public String toString() {
        return "Eagle{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
