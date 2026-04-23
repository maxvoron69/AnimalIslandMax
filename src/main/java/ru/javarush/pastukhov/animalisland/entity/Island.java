package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.GameConfig;

public class Island {
    private final Cell[][] cells;
    private final int width;
    private final int height;

    public Island() {
        this.width = GameConfig.getIslandWidth();
        this.height = GameConfig.getIslandHeight();
        this.cells = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return cells[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}