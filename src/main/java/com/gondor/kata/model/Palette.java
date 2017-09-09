package com.gondor.kata.model;

/**
 * Created by coding on 09/09/2017.
 */
public enum Palette {
    GLOSS("G"),
    MATTE("M");

    private final String name;

    Palette(String name) {
        this.name = name;
    }

    public static boolean isValid(String name) {
        try {
            Palette.valueOf(name);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static Palette from(String str) {

        for (Palette palette : Palette.values()) {
            if (palette.name.equals(str)) {
                return palette;
            }
        }

        throw new IllegalArgumentException();
    }

}