package com.gondor.kata.model;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * Created by coding on 09/09/2017.
 */
public class Color {
    private final String name;
    private final Palette palette;

    public Color(String name, Palette palette) {
        this.name = Preconditions.checkNotNull(name);
        this.palette = Preconditions.checkNotNull(palette);
    }

    public String name() {
        return name;
    }

    public Palette palette() {
        return palette;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(name, color.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Color(" +
                "\'" + name + "\'" +
                ",\' " + palette + "\'" +
                ')';
    }
}
