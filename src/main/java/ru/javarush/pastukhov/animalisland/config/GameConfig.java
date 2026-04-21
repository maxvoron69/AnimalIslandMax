package ru.javarush.pastukhov.animalisland.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class GameConfig {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = GameConfig.class
                .getClassLoader()
                .getResourceAsStream("game.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл game.properties не найден в resources!");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке game.properties", e);
        }
    }

    // === Размер острова ===
    public static int getIslandWidth() {
        return getInt("island.width");
    }

    public static int getIslandHeight() {
        return getInt("island.height");
    }

    // === Симуляция ===
    public static int getTickDelay() {
        return getInt("simulation.tick.delay");
    }

    public static int getMaxTurns() {
        return getInt("simulation.max.turns");
    }

    public static String getStopCondition() {
        return PROPERTIES.getProperty("simulation.stop.condition", "allDead");
    }

    public static int getInitialQuantityAnimal(String animalType) {
        return getInt("initial.animal." + animalType);
    }

     // === Количество детёнышей при размножении ===
    public static int getReproductionCount(String animalType) {
        return getInt("reproduction.count." + animalType);
    }

    // Универсальные приватные методы
    private static int getInt(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Конфигурация не найдена: " + key);
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Неверный формат числа в конфиге: " + key + " = " + value);
        }
    }

    private static double getDouble(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Конфигурация не найдена: " + key);
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Неверный формат числа в конфиге: " + key + " = " + value);
        }
    }
}
