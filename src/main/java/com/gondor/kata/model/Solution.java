package com.gondor.kata.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by coding on 09/09/2017.
 */
public class Solution {
    private final int totalColors;
    private final List<Customer> customers;
    private final Map<String, Color> colors;
    private Map<String, Color> result;

    private Solution(int totalColors, List<Customer> customers, Map<String, Color> colors, Map<String, Color> result) {
        this.totalColors = totalColors;
        this.customers = customers;
        this.colors = colors;
        this.result = result;
    }

    public Solution(int totalColors, List<Customer> customers) {
        this(totalColors, customers, new HashMap<>(), new HashMap<>());
    }

    public boolean isLeafNode() {
        return colors.size() == totalColors;
    }

    public boolean isGoalNode() {
        int satisfactionCount = 0;

        for (Customer customer : customers) {
            for (Color color : customer.colors()) {
                String key = color.name();
                Color colorInSolution = colors.get(key);
                if (colorInSolution.palette().equals(color.palette())) {
                    satisfactionCount++;
                    break;
                }
            }
        }

        String state = colors.entrySet()
                .stream()
                .map(color -> color.getValue().toString())
                .reduce("", (color1, color2) -> color1 + ":" + color2);
        System.out.println(state);

        if (satisfactionCount == customers.size()) {
            result = colors;
        }
        return satisfactionCount == customers.size();
    }

    public Map<String, Color> colors() {
        return new HashMap<>(colors);
    }

    public Map<String, Color> result() {
        return result;
    }

    public void addColor(String colorName, Palette domain) {
        colors.put(colorName, new Color(colorName, domain));
    }

    public Solution copy() {
        return new Solution(totalColors,
                new ArrayList<>(customers),
                new HashMap<>(colors),
                new HashMap<>(result));
    }

    public int totalMattes() {
        return (int) colors().keySet().stream()
                .filter(k -> colors().get(k).palette().equals(Palette.MATTE))
                .count();
    }
}
