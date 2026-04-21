package ru.javarush.pastukhov.animalisland.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PlantConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = PlantConfig.class
                .getClassLoader()
                .getResourceAsStream("plant.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл plant.properties не найден в resources!");
            }

            PROPERTIES.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке plant.properties", e);
        }
    }

    private static double getDouble(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Конфигурация не найдена: " + key);
        }
        return Double.parseDouble(value.trim());
    }

    private static int getInt(String key) {
        return (int) getDouble(key);
    }

    public static double getWeight() {
        return getDouble("plant.weight");
    }

    public static int getMaxCount() {
        return getInt("plant.maxCount");
    }

    public static double getGrowthRate() {
        return getDouble("plant.growthRate");
    }

    public static String getEmoji() {
        return PROPERTIES.getProperty("plant.emoji", "?");
    }
}


