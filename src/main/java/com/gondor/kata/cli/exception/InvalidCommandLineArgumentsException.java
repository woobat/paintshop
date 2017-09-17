package com.gondor.kata.cli.exception;

/**
 * Created by coding on 17/09/2017.
 */
public class InvalidCommandLineArgumentsException extends RuntimeException {

    public InvalidCommandLineArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}
