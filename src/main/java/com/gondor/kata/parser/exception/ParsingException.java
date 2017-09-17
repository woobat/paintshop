package com.gondor.kata.parser.exception;

import java.io.IOException;

/**
 * Created by coding on 09/09/2017.
 */
public class ParsingException extends Exception {

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, IOException e) {
        super(message, e);
    }

    public ParsingException(Exception e) {
        super(e);
    }
}

