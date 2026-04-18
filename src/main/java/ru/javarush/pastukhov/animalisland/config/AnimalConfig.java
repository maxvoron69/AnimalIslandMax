package ru.javarush.pastukhov.animalisland.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static javax.swing.UIManager.getInt;

public class AnimalConfig {

    private static final Properties PROPERTIES = new Properties();

    static {

        try (InputStream input = AnimalConfig.class
                .getClassLoader()
                .getResourceAsStream("animals.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл animals.properties не найден в resources!");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке animals.properties", e);
        }
    }

    public static double getDouble(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Конфигурация не найдена: " + key);
        }
        return Double.parseDouble(value.trim());
    }

        public static int getInt(String key) {
            return (int) getDouble(key);
    }

    public static double getWeight(String animalType) {
        return getDouble("animal." + animalType + ".weight");
    }

    public static int getMaxCount(String animalType) {
        return getInt("animal." + animalType + ".maxCount");
    }

    public static int getSpeed(String animalType) {
        return getInt("animal." + animalType + ".speed");
    }

    public static double getMaximumFoodLoad(String animalType) {
        return getDouble("animal." + animalType + ".maximumFoodLoad");
    }
}
