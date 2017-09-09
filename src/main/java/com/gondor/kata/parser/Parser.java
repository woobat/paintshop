package com.gondor.kata.parser;

import com.gondor.kata.model.Problem;
import com.gondor.kata.parser.exception.ParsingException;

import java.io.File;

/**
 * Created by coding on 09/09/2017.
 */
interface Parser {

    Problem parse(File file) throws ParsingException;
}
