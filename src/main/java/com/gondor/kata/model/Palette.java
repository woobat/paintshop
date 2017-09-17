package com.gondor.kata.model;

import java.util.Arrays;

/**
 * Created by coding on 09/09/2017.
 */
public enum Palette {
    GLOSS("G"),
    MATTE("M");

    private final String shortName;

    public String shortName() {
        return shortName;
    }

    Palette(String shortName) {
        this.shortName = shortName;
    }

    public static boolean isValid(String name) {
        try {
            Palette.valueOf(name);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidShortName(String name) {

        return Arrays.stream(Palette.values())
                .anyMatch(p -> p.shortName.equals(name));
    }

    public static Palette fromShortName(String str) {

        for (Palette palette : Palette.values()) {
            if (palette.shortName.equals(str)) {
                return palette;
            }
        }

        throw new IllegalArgumentException(String.format("Wrong Palette name = %s", str));
    }
}