package com.gondor.kata.model;

public enum Strategy {
    NAIVE_SEARCH("NAIVE_SEARCH"),
    BACKTRACKING_WITH_FORWARD_CHECKING("BACKTRACKING_WITH_FORWARD_CHECKING"),
    BACKTRACKING_WITH_FORWARD_CHECKING_AND_MRV("BACKTRACKING_WITH_FORWARD_CHECKING_AND_MRV");

    private final String name;

    Strategy(String name) {
        this.name = name;
    }

    public static boolean isValid(String name) {
        try {
            Strategy.valueOf(name);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}