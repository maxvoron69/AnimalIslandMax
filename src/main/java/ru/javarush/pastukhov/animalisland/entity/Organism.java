package ru.javarush.pastukhov.animalisland.entity;

import ru.javarush.pastukhov.animalisland.util.TranslationUtil;

public abstract class Organism {
    protected String type;

    public Organism(String type) {
        this.type = type;
    }

    public abstract Organism createNewInstance();

    public String getType() {
        return type;
    }

    public String getLocalizedType() {
        String type = getType();
        if (type == null) return "Неизвестно";
        String nominative = TranslationUtil.toNominativ(type);
        return TranslationUtil.capitalize(nominative != null ? nominative : type);
    }
}

