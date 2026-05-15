package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.PlantConfig;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Animals> animals = new ArrayList<>();
    private Plant plants;
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.plants = new Plant(PlantConfig.getPlantInitial());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public synchronized void addAnimal(Animals animal) {
        if (animal == null) {
            return;
        }
        animals.add(animal);
    }

    public synchronized List<Animals> getAnimals() {
        return new ArrayList<>(animals);
    }

    public synchronized boolean removeAnimal(Animals animal) {
        return animals.remove(animal);
    }

    public synchronized Plant getPlants() {
        return plants;
    }

    public synchronized void setPlants(Plant plants) {
        if (plants == null) {
            throw new IllegalArgumentException("Растения не могут быть null");
        }
        this.plants = plants;
    }

    public synchronized boolean hasPlantsAvailable() {
        return plants.getCurrentCount() > 0;
    }

    public synchronized void consumePlant() {
        plants = (Plant) plants.consume();
    }

    public synchronized void growPlant(int currentTurn) {
        if (plants.canGrow() && Math.random() < PlantConfig.getGrowthRate()) {
            plants = (Plant) plants.createNewInstance(currentTurn);
        }
    }

    @Override
    public synchronized String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", животных=" + animals.size() +
                ", растений=" + plants.getCurrentCount() +
                '}';
    }
}


