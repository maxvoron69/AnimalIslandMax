package ru.javarush.pastukhov.animalisland.util;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE;

    public static Direction getRandom() {
        return values()[(int) (GameUtils.RANDOM.nextDouble() * values().length)];
    }
}
