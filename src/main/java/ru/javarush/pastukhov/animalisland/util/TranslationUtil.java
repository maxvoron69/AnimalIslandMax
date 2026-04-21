package ru.javarush.pastukhov.animalisland.util;

public class TranslationUtil {
    public static String toRussian(String type) {
        return switch (type.toLowerCase()) {
            case "horse" -> "лошадь";
            case "sheep" -> "овцу";
            case "goat" -> "козу";
            case "rabbit" -> "кролика";
            case "deer" -> "оленя";
            case "mouse" -> "мышь";
            case "boar" -> "кабана";
            case "buffalo" -> "буйвола";
            case "duck" -> "утку";
            case "caterpillar" -> "гусеницу";

            case "wolf" -> "волк";
            case "fox" -> "лиса";
            case "bear" -> "медведь";
            case "eagle" -> "орёл";
            case "boa" -> "удав";

            default -> type;
        };
    }

    public static String toNominativ(String type) {
        return switch (type.toLowerCase()) {
            case "horse" -> "лошадь";
            case "sheep" -> "овцa";
            case "goat" -> "козa";
            case "rabbit" -> "кролик";
            case "deer" -> "олень";
            case "mouse" -> "мышь";
            case "boar" -> "кабан";
            case "buffalo" -> "буйвол";
            case "duck" -> "утка";
            case "caterpillar" -> "гусеница";

            case "wolf" -> "волк";
            case "fox" -> "лиса";
            case "bear" -> "медведь";
            case "eagle" -> "орёл";
            case "boa" -> "удав";

            default -> type;
        };
    }

    public static String toNominativPlural(String type) {
        return switch (type.toLowerCase()) {
            case "horse" -> "лошадей";
            case "sheep" -> "овец";
            case "goat" -> "коз";
            case "rabbit" -> "кроликов";
            case "deer" -> "оленей";
            case "mouse" -> "мышей";
            case "boar" -> "кабанов";
            case "buffalo" -> "буйволов";
            case "duck" -> "уток";
            case "caterpillar" -> "гусениц";

            case "wolf" -> "волков";
            case "fox" -> "лис";
            case "bear" -> "медведей";
            case "eagle" -> "орлов";
            case "boa" -> "удавов";

            default -> type;
        };
    }
    public static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}