package ru.javarush.pastukhov.animalisland;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");

        Logger logger = Logger.getLogger(Main.class.getName());
        logger.info("Симуляция острова запущена...");

        Simulation simulation = new Simulation();
        simulation.run();
    }
}
