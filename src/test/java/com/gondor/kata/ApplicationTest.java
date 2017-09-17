package com.gondor.kata;

import com.gondor.kata.cli.PaintShopOptions;
import com.gondor.kata.config.Constants;
import com.gondor.kata.model.*;
import com.gondor.kata.parser.exception.ParsingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by coding on 18/09/2017.
 */
public class ApplicationTest {

    private File createdFile;
    private String[] args;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        createdFile = folder.newFile("paintShopProblem.txt");
        args = new String[]{
                "--file_path",
                createdFile.getAbsolutePath(),
                "--strategy",
                "BACKTRACKING_WITH_FORWARD_CHECKING"
        };
    }

    @Test
    public void parseCommandLine() throws Exception {
        PaintShopOptions options = Application.parseCommandLine(args);
        assertEquals(createdFile.getAbsolutePath(), options.filePath);
        assertEquals("BACKTRACKING_WITH_FORWARD_CHECKING", options.strategy);
    }

    @Test
    public void loadProblem() throws Exception {
        File file = new File(getClass().getClassLoader()
                .getResource("has_solutions/valid_problem_5_colors_3_customers.txt").getFile());

        Problem problem = Application.loadProblem(file);
        assertEquals(3, problem.totalCustomers());
        assertEquals(5, problem.totalColors());
        assertEquals(2, problem.totalDomains());
    }

    @Test
    public void solveProblem() throws Exception {
        Color firstColor = new Color("1", Palette.GLOSS);
        Color secondColor = new Color("2", Palette.MATTE);

        Customer tom = new Customer("1", Arrays.asList(firstColor, secondColor));
        Problem problem = new Problem(Arrays.asList(tom),
                Arrays.asList("1", "2"),
                Arrays.asList(Palette.values()));

        Optional<Solution> solution = Application.solveProblem(Strategy.NAIVE_SEARCH, problem);
        assertEquals(true, solution.isPresent());
        assertEquals(0, solution.get().totalMattes());
        assertEquals(Palette.GLOSS, solution.get().colors().get("1").palette());
        assertEquals(Palette.GLOSS, solution.get().colors().get("2").palette());
    }

    @Test
    public void testValidSolution() throws Exception {
        /**
         * load a known solution check whether output is displayed correctly
         */
        Optional<Solution> solution = loadProblemAndGetSolution("has_solutions/valid_problem_5_colors_3_customers.txt");
        String result = Application.outputSolution(solution);

        assertEquals("G G G G M", result);
    }

    @Test
    public void testProblemHavingNoSolution() throws Exception {
        /**
         * load a unknown solution check whether output is displayed correctly
         */
        Optional<Solution> solution = loadProblemAndGetSolution("without_solutions/no_solution_1_color_2_customers.txt");
        String result = Application.outputSolution(solution);

        assertEquals(Constants.NO_SOLUTION, result);
    }

    private Optional<Solution> loadProblemAndGetSolution(String fileName) throws ParsingException {
        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        Problem problem = Application.loadProblem(file);
        return Application.solveProblem(Strategy.BACKTRACKING_WITH_FORWARD_CHECKING, problem);
    }

}