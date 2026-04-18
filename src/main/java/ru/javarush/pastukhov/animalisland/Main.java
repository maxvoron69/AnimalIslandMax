package ru.javarush.pastukhov.animalisland;

import ru.javarush.pastukhov.animalisland.entity.Sheep;

public class Main {
    public static void main(String[] args) {
        Sheep sheep = new Sheep(8);
        System.out.println(sheep);

        sheep.eat();

        Sheep pup = (Sheep) sheep.reproduce();
        if (pup != null) {
            System.out.println("Родилась овца: " + pup);
        }
    }
}
