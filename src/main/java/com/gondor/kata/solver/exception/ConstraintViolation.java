package com.gondor.kata.solver.exception;

/**
 * Created by coding on 17/09/2017.
 */
public class ConstraintViolation extends Exception {

    private final String message;

    public ConstraintViolation(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
