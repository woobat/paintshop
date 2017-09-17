package com.gondor.kata;

import com.gondor.kata.cli.CommandLineParser;
import com.gondor.kata.cli.PaintShopOptions;
import com.gondor.kata.cli.exception.InvalidCommandLineArgumentsException;
import com.gondor.kata.model.Problem;
import com.gondor.kata.model.Solution;
import com.gondor.kata.model.Strategy;
import com.gondor.kata.output.OutputFormatter;
import com.gondor.kata.output.TextOutput;
import com.gondor.kata.parser.FileParser;
import com.gondor.kata.parser.exception.ParsingException;
import com.gondor.kata.solver.Solver;
import com.gondor.kata.solver.SolverFactory;
import com.google.devtools.common.options.OptionsParser;

import java.io.File;
import java.util.Optional;

/**
 * Created by coding on 18/09/2017.
 */
public class Application {

    public static PaintShopOptions parseCommandLine(String[] args) {
        PaintShopOptions options = CommandLineParser.parse(args);
        System.out.format("Solving problem at %s using strategy %s...\n", options.filePath, options.strategy);
        return options;
    }

    public static Problem loadProblem(File filePath) throws ParsingException {
        FileParser parser = new FileParser();
        return parser.parse(filePath);
    }
    
    public static Optional<Solution> solveProblem(Strategy strategy, Problem problem) {
        Solver solver = SolverFactory.solver(strategy, problem);
        solver.solve();
        return solver.solution();
    }

    public static String outputSolution(Optional<Solution> solution) {
        OutputFormatter outputFormatter = new TextOutput(solution);
        return outputFormatter.printResult();
    }

    public static void main(String args[]) {

        try {
            PaintShopOptions options = parseCommandLine(args);

            File inputFile = new File(options.filePath);
            Problem problem = loadProblem(inputFile);

            Strategy strategy = Strategy.valueOf(options.strategy);
            Optional<Solution> solution = solveProblem(strategy, problem);

            String palettePicks = outputSolution(solution);

            System.out.println(palettePicks);
        } catch (InvalidCommandLineArgumentsException e) {
            CommandLineParser.printUsage(OptionsParser.newOptionsParser(PaintShopOptions.class), e.getMessage());
        } catch (ParsingException e) {
            System.out.println(e.getMessage());
        }
    }

}