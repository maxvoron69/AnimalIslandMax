package ru.javarush.pastukhov.animalisland.entity.herbivores;

import ru.javarush.pastukhov.animalisland.config.PredatorFoodConfig;
import ru.javarush.pastukhov.animalisland.entity.Animals;
import ru.javarush.pastukhov.animalisland.entity.Cell;
import ru.javarush.pastukhov.animalisland.entity.Herbivores;
import ru.javarush.pastukhov.animalisland.entity.Organism;
import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duck extends Herbivores {

    private static final Logger LOGGER = Logger.getLogger(Duck.class.getName());

    public Duck() {
        super("duck");
    }

    @Override
    public boolean eat() {
        // Сначала пробуем съесть растение
        if (super.eat()) {
            resetHunger();
            return true;
        }

        // Если нет — охотимся на гусениц
        Cell cell = getCurrentCell();
        if (cell != null) {
            return huntForCaterpillars(cell);
        }
        return false;
    }

    private boolean huntForCaterpillars(Cell cell) {
        List<Animals> animals = cell.getAnimals();
        for (Animals animal : animals) {
            if (animal instanceof Caterpillar && animal != this) {
                double successRate = PredatorFoodConfig.getSuccessRate("duck", "caterpillar");
                if (GameUtils.RANDOM.nextDouble() < successRate) {
                    cell.removeAnimal(animal);
                    resetHunger();
                    LOGGER.log(Level.INFO, "Утка съела гусеницу в клетке (" + cell.getX() + ", " + cell.getY() + ")");
                    return true;
                } else {
                    LOGGER.log(Level.INFO, "Утка промахнулась при ловле гусеницы");
                }
            }
        }
        return false;
    }

    private Cell currentCell;

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    @Override
    public Organism createNewInstance() {
        Duck duck = new Duck();
        duck.setPosition(this.getX(), this.getY());
        LOGGER.log(Level.INFO, "Родился утёнок!");
        return duck;
    }

    @Override
    public String toString() {
        return "Duck{" +
                " weight=" + getWeight() +
                '}';
    }
}
