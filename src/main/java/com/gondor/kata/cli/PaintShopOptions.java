package com.gondor.kata.cli;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

/**
 * Command-line options definition for the PaintShop Application.
 */
public class PaintShopOptions extends OptionsBase {

    @Option(name = "help",
            abbrev = 'h',
            help = "Prints usage info.",
            defaultValue = "true"
    )
    public boolean help;

    @Option(
            name = "file_path",
            abbrev = 'f',
            help = "The input file full path.",
            category = "startup",
            defaultValue = ""
    )
    public String filePath;

    @Option(
            name = "strategy",
            abbrev = 's',
            help = "options - NAIVE_SEARCH, BACKTRACKING_WITH_FORWARD_CHECKING, BACKTRACKING_WITH_FORWARD_CHECKING_AND_MRV.",
            category = "startup",
            defaultValue = "BACKTRACKING_WITH_FORWARD_CHECKING"
    )
    public String strategy;
}