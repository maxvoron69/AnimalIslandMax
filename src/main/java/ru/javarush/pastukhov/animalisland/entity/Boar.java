package ru.javarush.pastukhov.animalisland.entity;

public class Boar extends Herbivores{


    public Boar(int currentCount) {
        super("boar", currentCount);
    }

    @Override
    public boolean eat() {
        System.out.println("Кабан ест жёлуди...");
        return true;
    }

    @Override
    protected Organism createNewInstance() {
        return new Boar(1);
    }

    @Override
    public String toString() {
        return "Boar{" +
                "count=" + getCurrentCount() +
                ", weight=" + getWeight() +
                '}';
    }
}
