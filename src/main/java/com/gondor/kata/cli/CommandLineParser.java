package com.gondor.kata.cli;

import com.gondor.kata.cli.exception.InvalidCommandLineArgumentsException;
import com.gondor.kata.model.Strategy;
import com.google.devtools.common.options.OptionsParser;

import java.io.File;
import java.util.Collections;

/**
 * Created by coding on 17/09/2017.
 */
public class CommandLineParser {

    private static boolean isNotFile(String filePath) {
        File inputFile = new File(filePath);
        return !inputFile.isFile();
    }

    public static void printUsage(OptionsParser parser, String errorMessage) {
        if (!errorMessage.isEmpty()) {
            System.out.println(errorMessage);
        }

        System.out.println("Usage: java -jar paintshop-1.0-SNAPSHOT.jar OPTIONS");

        System.out.println(parser.describeOptions(Collections.emptyMap(),
                OptionsParser.HelpVerbosity.LONG));
    }

    public static PaintShopOptions parse(String[] args) {
        OptionsParser optionParser = OptionsParser.newOptionsParser(PaintShopOptions.class);
        optionParser.parseAndExitUponError(args);

        PaintShopOptions options = optionParser.getOptions(PaintShopOptions.class);
        StringBuilder sbErrorMessage = new StringBuilder();

        if (options.filePath.isEmpty()) {
            sbErrorMessage.append("file path cannot be empty.\n");
        }

        if (isNotFile(options.filePath)) {
            sbErrorMessage.append("file path (")
                    .append(options.filePath)
                    .append(") does not represent correct file path.\n");
        }

        if (!Strategy.isValid(options.strategy)) {
            sbErrorMessage.append("strategy (")
                    .append(options.strategy)
                    .append(") is not valid.\n");
        }

        String errorMessage = sbErrorMessage.toString();
        if (!errorMessage.isEmpty()) {
            throw new InvalidCommandLineArgumentsException(errorMessage);
        }

        return options;
    }
}