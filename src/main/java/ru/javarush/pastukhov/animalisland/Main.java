package ru.javarush.pastukhov.animalisland;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        configureLogging();
        System.out.println("Симуляция острова запущена...");

        Simulation simulation = new Simulation();
        simulation.start();

        Runtime.getRuntime().addShutdownHook(new Thread(simulation::stop));
    }

    private static void configureLogging() {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("logging.properties")) {
            if (is == null) {
                System.err.println("Файл logging.properties не найден в classpath. Используется стандартная конфигурация.");
                return;
            }
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            System.err.println("Не удалось загрузить конфигурацию логирования: " + e.getMessage());
        }
    }
}
