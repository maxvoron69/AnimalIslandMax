package ru.javarush.pastukhov.animalisland.service;

import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.config.GameConfig;
import ru.javarush.pastukhov.animalisland.entity.*;
import ru.javarush.pastukhov.animalisland.util.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AnimalSpawner {
    private static final Logger LOGGER = Logger.getLogger(AnimalSpawner.class.getName());

    public void spawnAll(Island island) {
        LOGGER.info("Инициализация животных начинается.");

        List<Animals> animals = new ArrayList<>();

        for (String animalType : AnimalConfig.getAnimalTypes()) {
            Class<? extends Animals> animalClass = getAnimalClass(animalType);
            int count = GameConfig.getInitialQuantityAnimal(animalType);

            for (int i = 0; i < count; i++) {
                try {
                    Animals animal = animalClass.getDeclaredConstructor().newInstance();
                    animals.add(animal);
                } catch (Exception e) {
                    LOGGER.severe("Не удалось создать экземпляр: " + animalType);
                }
            }
        }

        for (Animals animal : animals) {
            int x = GameUtils.RANDOM.nextInt(island.getWidth());
            int y = GameUtils.RANDOM.nextInt(island.getHeight());
            animal.setPosition(x, y);
            Cell cell = island.getCell(x, y);
            if (cell != null) {
                cell.addAnimal(animal);
                LOGGER.info("Размещаем " + animal.getType() + " в (" + x + ", " + y + ")");
            }
        }

        LOGGER.info("Инициализация завершена.");
    }

    private Class<? extends Animals> getAnimalClass(String type) {
        return switch (type) {
            case "wolf" -> Wolf.class;
            case "sheep" -> Sheep.class;
            case "rabbit" -> Rabbit.class;
            case "fox" -> Fox.class;
            case "bear" -> Bear.class;
            case "boar" -> Boar.class;
            case "horse" -> Horse.class;
            case "deer" -> Deer.class;
            case "goat" -> Goat.class;
            case "duck" -> Duck.class;
            case "eagle" -> Eagle.class;
            case "mouse" -> Mouse.class;
            case "buffalo" -> Buffalo.class;
            case "boa" -> Boa.class;
            case "caterpillar" -> Caterpillar.class;
            default -> throw new IllegalArgumentException("Неизвестный тип: " + type);
        };
    }
}