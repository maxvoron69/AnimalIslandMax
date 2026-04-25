package ru.javarush.pastukhov.animalisland.service;

import ru.javarush.pastukhov.animalisland.entity.Animals;
import ru.javarush.pastukhov.animalisland.entity.Cell;
import ru.javarush.pastukhov.animalisland.entity.Island;
import ru.javarush.pastukhov.animalisland.config.AnimalConfig;
import ru.javarush.pastukhov.animalisland.config.PlantConfig;

import java.util.HashMap;
import java.util.Map;

public class IslandRenderer {
    private static final int CELL_WIDTH = 20;

    public void render(Island island) {
        System.out.println("\n=== ОСТРОВ (" + island.getWidth() + "x" + island.getHeight() + ") ===");
        for (int y = 0; y < island.getHeight(); y++) {
            System.out.print("[");
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = island.getCell(x, y);
                String content = formatCell(cell);
                System.out.printf("%-" + CELL_WIDTH + "s", content);
                if (x < island.getWidth() - 1) System.out.print(" | ");
            }
            System.out.println("]");
        }
        System.out.println("===================================");
    }

    private String formatCell(Cell cell) {
        if (cell == null) return " ".repeat(CELL_WIDTH);
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> animalCounts = new HashMap<>();
        for (Animals a : cell.getAnimals()) {
            if (a != null) {
                animalCounts.merge(a.getType(), 1, Integer::sum);
            }
        }

        for (Map.Entry<String, Integer> entry : animalCounts.entrySet()) {
            String type = entry.getKey();
            int count = entry.getValue();
            String emoji = AnimalConfig.getAnimalEmoji(type);
            sb.append(emoji);
            if (count > 1) {
                sb.append(count);
            }
        }

        int plants = cell.getPlants().getCurrentCount();
        if (plants > 0) {
            sb.append(" ").append(PlantConfig.getEmoji()).append(plants);
        }

        return sb.length() > 0 ? sb.toString() : " ";
    }
}