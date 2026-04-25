package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.config.GameConfig;

import java.util.function.BiConsumer;

public class Island {
    private final Cell[][] cells;
    private final int width;
    private final int height;

    public Island() {
        this.width = GameConfig.getIslandWidth();
        this.height = GameConfig.getIslandHeight();

        if (width <= 0 || height <= 0) {
        throw new IllegalStateException("Размер острова должен быть > 0: " + width + "x" + height);
    }

        this.cells = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Координаты за пределами острова: (" + x + ", " + y + ")");
        }
        return cells[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void forEachCell(BiConsumer<Integer, Integer> action) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                action.accept(x, y);
            }
        }
    }
}