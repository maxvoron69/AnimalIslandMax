package ru.javarush.pastukhov.animalisland.service;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.entity.*;
import ru.javarush.pastukhov.animalisland.util.Direction;

import java.util.List;

public class AnimalMover {
    public void moveAll(Island island, List<Animals> allAnimals) {
        for (Animals animal : allAnimals) {
            int[] pos = animal.getPosition();
            if (pos != null) {
                moveAnimal(animal, pos[0], pos[1], island);
            }
        }
    }

    private void moveAnimal(Animals animal, int fromX, int fromY, Island island) {
        int currentX = fromX, currentY = fromY;
        int speed = AnimalConfig.getSpeed(animal.getType());

        for (int step = 0; step < speed; step++) {
            Direction direction = animal.chooseDirection();
            if (direction == Direction.NONE) break;

            int newX = currentX, newY = currentY;
            switch (direction) {
                case UP -> newY = Math.max(0, currentY - 1);
                case DOWN -> newY = Math.min(island.getHeight() - 1, currentY + 1);
                case LEFT -> newX = Math.max(0, currentX - 1);
                case RIGHT -> newX = Math.min(island.getWidth() - 1, currentX + 1);
            }

            Cell fromCell = island.getCell(currentX, currentY);
            Cell toCell = island.getCell(newX, newY);

            if (fromCell == null || toCell == null) {
                continue;
            }

            fromCell.getAnimals().remove(animal);
            toCell.addAnimal(animal);
            animal.setPosition(newX, newY);
            currentX = newX;
            currentY = newY;


            if (animal instanceof Duck) ((Duck) animal).setCurrentCell(toCell);
            if (animal instanceof Herbivores) ((Herbivores) animal).setCurrentCell(toCell);


        }

        int[] finalPos = animal.getPosition();
        if (finalPos[0] != fromX || finalPos[1] != fromY) {
            System.out.printf("%s переместился из (%d, %d) в (%d, %d)%n",
                    animal.getLocalizedType(), fromX, fromY, finalPos[0], finalPos[1]);
        }
    }
}
