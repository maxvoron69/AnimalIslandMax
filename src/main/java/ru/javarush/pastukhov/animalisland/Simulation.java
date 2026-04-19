package ru.javarush.pastukhov.animalisland;

import ru.javarush.pastukhov.animalisland.config.GameConfig;
import ru.javarush.pastukhov.animalisland.config.PlantConfig;
import ru.javarush.pastukhov.animalisland.entity.*;
import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.List;

public class Simulation {
    private final Island island;
    private int currentTurn = 0;

    public Simulation() {
        this.island = new Island();
        initializeAnimals();
    }

    Cell cell = new Cell();

    private void initializeAnimals() {
        // Добавим одну овцу и одного волка для теста
        Sheep sheep = new Sheep(1);
        Wolf wolf = new Wolf(1);

        island.getCell(0, 0).addAnimal(sheep);
        island.getCell(0, 0).addAnimal(wolf);
    }

    public void run() {
        while (currentTurn < 10) {
            System.out.println("\n--- Ход " + (++currentTurn) + " ---");
            processTurn();
            try {
                Thread.sleep(GameConfig.getTickDelay());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processTurn() {
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                Cell cell = island.getCell(x, y);
                processCell(cell);
            }
        }
    }

    private void processCell(Cell cell) {
        List<Animals> animals = cell.getAnimals();
        Plant plant = cell.getPlants(); // один объект Plant

        // --- Охота ---
        for (Animals predator : animals) {
            if (predator instanceof Predators) {
                for (Animals prey : animals) {
                    if (predator != prey) {
                        ((Predators) predator).eat(prey.getType());
                    }
                }
            }
        }

        // --- Травоядные едят ---
        for (Animals herbivore : animals) {
            if (herbivore instanceof Herbivores && cell.hasPlantsAvailable()) {
                boolean ate = herbivore.eat(); // по вероятности
                if (ate) {
                    plant.consume(); // уменьшает количество на 1
                }
            }
        }

        // --- Размножение растений ---
        if (plant.canGrow() && GameUtils.RANDOM.nextDouble() < PlantConfig.getGrowthRate()) {
            Plant updated = (Plant) plant.createNewInstance();
            cell.setPlants(updated); // обновляем состояние
        }

        // --- Движение ---
        for (Animals animal : animals) {
            System.out.println(animal.getType() + " двигается: " + animal.chooseDirection());
        }

        // --- Размножение животных ---
        for (Animals animal : animals) {
            animal.reproduce();
        }
    }
}