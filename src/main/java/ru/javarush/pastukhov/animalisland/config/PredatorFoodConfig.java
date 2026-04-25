package ru.javarush.pastukhov.animalisland.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PredatorFoodConfig {
    private static final Map<String, Map<String, Double>> PREDATOR_EATING_RATES = new HashMap<>();

    static {
        Properties props = new Properties();
        try (InputStream input = PredatorFoodConfig.class
                .getClassLoader()
                .getResourceAsStream("animals.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл animals.properties не найден!");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки animals.properties", e);
        }

        for (String key : props.stringPropertyNames()) {
            if (key.startsWith("predator.") && key.contains(".eats.")) {
                String[] parts = key.split("\\.");
                String predator = parts[1];
                String prey = parts[3];
                double rate = Double.parseDouble(props.getProperty(key));

                PREDATOR_EATING_RATES
                        .computeIfAbsent(predator, k -> new HashMap<>())
                        .put(prey, rate);
            }
        }
    }

    public static double getSuccessRate(String predator, String prey) {
        return PREDATOR_EATING_RATES
                .getOrDefault(predator, new HashMap<>())
                .getOrDefault(prey, 0.0);
    }

    public static Set<String> getPreyTypes(String predator) {
        Map<String, Double> preyRates = PREDATOR_EATING_RATES.get(predator);
        if (preyRates == null || preyRates.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(preyRates.keySet()));
    }

    public static boolean canEat(String predator, String prey) {
        return getSuccessRate(predator, prey) > 0;
    }
}
