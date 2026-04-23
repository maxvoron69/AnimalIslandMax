package ru.javarush.pastukhov.animalisland.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class AnimalConfig {

    private static final Properties PROPERTIES = new Properties();

    static {

        try (InputStream input = AnimalConfig.class
                .getClassLoader()
                .getResourceAsStream("animals.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл animals.properties не найден в resources!");
            }
            PROPERTIES.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке animals.properties", e);
        }
    }

    public static List<String> getAnimalTypes() {
        return PROPERTIES.keySet().stream()
                .map(Object::toString)
                .filter(key -> key.endsWith(".emoji"))
                .map(key -> key.replace(".emoji", ""))
                .distinct()
                .collect(Collectors.toList());
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

    public static double getHerbivoreBaseEatingSuccessRate() {
        return getDouble("herbivore.base.eating.success.rate");
    }

    public static String getAnimalEmoji(String animalType) {
        return PROPERTIES.getProperty(animalType + ".emoji", "?");
    }

}

