package com.gondor.kata.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by coding on 09/09/2017.
 */
public class Customer {
    private final String name;
    private final List<Color> colors;

    public Customer(String name, List<Color> colors) {
        this.name = Preconditions.checkNotNull(name);
        this.colors = Preconditions.checkNotNull(colors);
    }

    public String name() {
        return name;
    }

    public List<Color> colors() {
        return ImmutableList.copyOf(colors);
    }

    private Map<String, Palette> colorMap() {
        return colors().stream()
                .collect(Collectors.toMap(Color::name, Color::palette));
    }

    public int totalColors() {
        return colors().size();
    }

    public boolean hasColor(String colorName) {
        return colorMap().containsKey(colorName);
    }

    public boolean hasColorWithPalette(Color color) {
        if (colorMap().containsKey(color.name())) {
            return colorMap().get(color.name()).equals(color.palette());
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equal(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static Customer copyOf(Customer customer) {
        return new Customer(customer.name(), customer.colors());
    }

    public static Customer removeColor(Customer customer, String color) {

        String customerName = customer.name();
        List<Color> colors = customer.colors();

        List<Color> restColors = colors.stream()
                .filter(c -> !c.name().equals(color))
                .collect(Collectors.toList());

        return new Customer(customerName, restColors);
    }
}

