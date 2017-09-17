package com.gondor.kata.solver;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.parser.FileParser;
import com.gondor.kata.parser.exception.ParsingException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the solvers with relatively small input files.
 * <p>
 * Created by coding on 17/09/2017.
 */
public abstract class AbstractSolverTest {

    protected Solver solver;
    private FileParser fileParser = new FileParser();

    abstract Solver getSolver(Problem problem);

    public void solvePaintShopProblem(String inputFile) throws ParsingException {
        File file = new File(getClass().getClassLoader().getResource(inputFile).getFile());
        Problem problem = fileParser.parse(file);

        solver = getSolver(problem);
        solver.solve();
    }

    @Before
    public void setup() {
        fileParser = new FileParser();
    }

    @Test
    public void oneCustomerWithOnlyOneColors() throws ParsingException {
        solvePaintShopProblem("has_solutions/valid_problem_1_color_1_customer.txt");

        assertEquals(true, solver.isSolvable());

        Map<String, Color> map = solver.solution().get().colors();
        assertEquals(Palette.MATTE, map.get("1").palette());
    }

    @Test
    public void oneCustomerWithOnlyManyColors() throws ParsingException {
        solvePaintShopProblem("has_solutions/valid_problem_N_color_1_customer.txt");

        assertEquals(true, solver.isSolvable());

        Map<String, Color> map = solver.solution().get().colors();
        assertEquals(Palette.GLOSS, map.get("1").palette());
        assertEquals(Palette.GLOSS, map.get("2").palette());
        assertEquals(Palette.GLOSS, map.get("3").palette());
        assertEquals(Palette.GLOSS, map.get("4").palette());
        assertEquals(Palette.GLOSS, map.get("5").palette());
        assertEquals(Palette.GLOSS, map.get("6").palette());
        assertEquals(Palette.GLOSS, map.get("7").palette());
        assertEquals(Palette.GLOSS, map.get("8").palette());
        assertEquals(Palette.GLOSS, map.get("9").palette());
        assertEquals(Palette.GLOSS, map.get("10").palette());
    }

    @Test
    public void testValidProblemWithFiveColorsWithThreeCustomers() throws ParsingException {
        solvePaintShopProblem("has_solutions/valid_problem_5_colors_3_customers.txt");

        assertEquals(true, solver.isSolvable());

        Map<String, Color> map = solver.solution().get().colors();

        assertEquals(Palette.GLOSS, map.get("1").palette());
        assertEquals(Palette.GLOSS, map.get("2").palette());
        assertEquals(Palette.GLOSS, map.get("3").palette());
        assertEquals(Palette.GLOSS, map.get("4").palette());
        assertEquals(Palette.MATTE, map.get("5").palette());
    }

    @Test
    public void testValidProblemWithFiveColorsWithManyCustomers() throws ParsingException {
        solvePaintShopProblem("has_solutions/valid_problem_5_colors_14_customers.txt");

        Map<String, Color> map = solver.solution().get().colors();

        assertEquals(true, solver.isSolvable());
        assertEquals(Palette.GLOSS, map.get("1").palette());
        assertEquals(Palette.MATTE, map.get("2").palette());
        assertEquals(Palette.GLOSS, map.get("3").palette());
        assertEquals(Palette.MATTE, map.get("4").palette());
        assertEquals(Palette.GLOSS, map.get("5").palette());
    }

    @Test
    public void canTraverseSearchSpace() throws ParsingException {
        /**
         * Here the first solution that is reachable by the backtracking algorithm is not optimal
         * i.e. 1G 2G 3G 4M 5M which needs two matte. However, for the case of a optimal solution
         * it only needs one matte i.e. 1M
         *
         * There was a glitch in the logic and algorithm was not traversing the search space. So add this
         * failing test to ensure regression does not happen.
         */
        solvePaintShopProblem("has_solutions/first_solution_that_is_not_optimal.txt");

        Map<String, Color> map = solver.solution().get().colors();

        assertEquals(true, solver.isSolvable());
        assertEquals(Palette.MATTE, map.get("1").palette());
        assertEquals(Palette.GLOSS, map.get("2").palette());
        assertEquals(Palette.GLOSS, map.get("3").palette());
        assertEquals(Palette.GLOSS, map.get("4").palette());
        assertEquals(Palette.GLOSS, map.get("5").palette());
    }

    @Test
    public void canHandleNoSolution() throws ParsingException {
        solvePaintShopProblem("without_solutions/no_solution_1_color_2_customers.txt");

        assertEquals(false, solver.isSolvable());
        assertEquals(Optional.empty(), solver.solution());
    }

    @Test
    public void canHandleNoSolutionWithManyColors() throws ParsingException {
        solvePaintShopProblem("without_solutions/no_solution_more_than_one_color.txt");

        assertEquals(false, solver.isSolvable());
        assertEquals(Optional.empty(), solver.solution());
    }

    @Test
    public void canHandleAllGloss() throws ParsingException {
        solvePaintShopProblem("has_solutions/valid_problem_all_gloss.txt");

        assertEquals(true, solver.isSolvable());

        Map<String, Color> map = solver.solution().get().colors();
        assertEquals(Palette.GLOSS, map.get("1").palette());
        assertEquals(Palette.GLOSS, map.get("2").palette());
        assertEquals(Palette.GLOSS, map.get("3").palette());
        assertEquals(Palette.GLOSS, map.get("4").palette());
        assertEquals(Palette.GLOSS, map.get("5").palette());
        assertEquals(Palette.GLOSS, map.get("6").palette());
        assertEquals(Palette.GLOSS, map.get("7").palette());
        assertEquals(Palette.GLOSS, map.get("8").palette());
        assertEquals(Palette.GLOSS, map.get("9").palette());
        assertEquals(Palette.GLOSS, map.get("10").palette());
    }

    @Test
    public void canHandleAllMatte() throws ParsingException {
        solvePaintShopProblem("has_solutions/valid_problem_all_matte.txt");

        assertEquals(true, solver.isSolvable());

        Map<String, Color> map = solver.solution().get().colors();
        assertEquals(Palette.MATTE, map.get("1").palette());
        assertEquals(Palette.MATTE, map.get("2").palette());
        assertEquals(Palette.MATTE, map.get("3").palette());
        assertEquals(Palette.MATTE, map.get("4").palette());
        assertEquals(Palette.MATTE, map.get("5").palette());
        assertEquals(Palette.MATTE, map.get("6").palette());
        assertEquals(Palette.MATTE, map.get("7").palette());
        assertEquals(Palette.MATTE, map.get("8").palette());
        assertEquals(Palette.MATTE, map.get("9").palette());
        assertEquals(Palette.MATTE, map.get("10").palette());
    }

    @Test
    public void testWithInterleavedPalette() throws ParsingException {
        solvePaintShopProblem("has_solutions/valid_problem_interleaved_palette.txt");

        assertEquals(true, solver.isSolvable());

        Map<String, Color> map = solver.solution().get().colors();
        assertEquals(Palette.GLOSS, map.get("1").palette());
        assertEquals(Palette.MATTE, map.get("2").palette());
        assertEquals(Palette.GLOSS, map.get("3").palette());
        assertEquals(Palette.MATTE, map.get("4").palette());
        assertEquals(Palette.GLOSS, map.get("5").palette());
        assertEquals(Palette.MATTE, map.get("6").palette());
        assertEquals(Palette.GLOSS, map.get("7").palette());
        assertEquals(Palette.MATTE, map.get("8").palette());
        assertEquals(Palette.GLOSS, map.get("9").palette());
        assertEquals(Palette.MATTE, map.get("10").palette());
    }
}