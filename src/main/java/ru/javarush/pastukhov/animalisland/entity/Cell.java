package ru.javarush.pastukhov.animalisland.entity;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Animals> animals = new ArrayList<>();
    private Plant plants;

    public Cell(){
        this.plants=new Plant(10);
    }

    public void addAnimal(Animals animal) {
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
                "животных=" + animals.size() +
                ", растений=" + plants.getCurrentCount() +
                '}';
    }
}


