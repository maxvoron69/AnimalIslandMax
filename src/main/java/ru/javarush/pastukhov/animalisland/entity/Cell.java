package ru.javarush.pastukhov.animalisland.entity;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Animals> animals = new ArrayList<>();
    private Plant plants;
    private final int x;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.plants = new Plant(10); // начальное количество растений
    }

    private final int y;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addAnimal(Animals animal) {
        if (animal == null) {
            return;
        }
        animals.add(animal);
    }

    public List<Animals> getAnimals() {
        return animals;
    }

    public Plant getPlants() {
        return plants;
    }

    public void setPlants(Plant plants) {
        if (plants == null) {
            throw new IllegalArgumentException("Растения не могут быть null");
        }
        this.plants = plants;
    }

    public boolean hasPlantsAvailable() {
        return plants.getCurrentCount() > 0;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", животных=" + animals.size() +
                ", растений=" + plants.getCurrentCount() +
                '}';
    }
}


