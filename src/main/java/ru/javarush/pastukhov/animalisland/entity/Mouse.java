package ru.javarush.pastukhov.animalisland.entity;

public class Mouse extends Herbivores{

    public Mouse(int currentCount) {
        super("mouse", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Мышь ест пищу...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new  Mouse(1);
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
