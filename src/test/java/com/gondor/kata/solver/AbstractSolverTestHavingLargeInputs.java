package com.gondor.kata.solver;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Palette;
import com.gondor.kata.parser.exception.ParsingException;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test for solvers that can handle large inputs.
 * <p>
 * Created by coding on 18/09/2017.
 */
public abstract class AbstractSolverTestHavingLargeInputs extends AbstractSolverTest {

    @Test
    public void testCanHandleTwentyCustomersAllGloss() throws ParsingException {

        solvePaintShopProblem("large_inputs/20_colors_20_customers_all_gloss.txt");

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
        assertEquals(Palette.GLOSS, map.get("11").palette());
        assertEquals(Palette.GLOSS, map.get("12").palette());
        assertEquals(Palette.GLOSS, map.get("13").palette());
        assertEquals(Palette.GLOSS, map.get("14").palette());
        assertEquals(Palette.GLOSS, map.get("15").palette());
        assertEquals(Palette.GLOSS, map.get("16").palette());
        assertEquals(Palette.GLOSS, map.get("17").palette());
        assertEquals(Palette.GLOSS, map.get("18").palette());
        assertEquals(Palette.GLOSS, map.get("19").palette());
        assertEquals(Palette.GLOSS, map.get("20").palette());
    }

    @Test
    public void testCanHandleTwentyCustomersMixedPalette() throws ParsingException {

        solvePaintShopProblem("large_inputs/20_colors_20_customers_mixed_palette.txt");

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
        assertEquals(Palette.GLOSS, map.get("11").palette());
        assertEquals(Palette.GLOSS, map.get("12").palette());
        assertEquals(Palette.GLOSS, map.get("13").palette());
        assertEquals(Palette.GLOSS, map.get("14").palette());
        assertEquals(Palette.GLOSS, map.get("15").palette());
        assertEquals(Palette.GLOSS, map.get("16").palette());
        assertEquals(Palette.GLOSS, map.get("17").palette());
        assertEquals(Palette.GLOSS, map.get("18").palette());
        assertEquals(Palette.MATTE, map.get("19").palette());
        assertEquals(Palette.GLOSS, map.get("20").palette());
    }

    @Test
    public void testCanHandleFiftyCustomers() throws ParsingException {

        solvePaintShopProblem("large_inputs/50_colors_50_customers_all_gloss.txt");

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
        assertEquals(Palette.GLOSS, map.get("11").palette());
        assertEquals(Palette.GLOSS, map.get("12").palette());
        assertEquals(Palette.GLOSS, map.get("13").palette());
        assertEquals(Palette.GLOSS, map.get("14").palette());
        assertEquals(Palette.GLOSS, map.get("15").palette());
        assertEquals(Palette.GLOSS, map.get("16").palette());
        assertEquals(Palette.GLOSS, map.get("17").palette());
        assertEquals(Palette.GLOSS, map.get("18").palette());
        assertEquals(Palette.GLOSS, map.get("19").palette());
        assertEquals(Palette.GLOSS, map.get("20").palette());
        assertEquals(Palette.GLOSS, map.get("21").palette());
        assertEquals(Palette.GLOSS, map.get("22").palette());
        assertEquals(Palette.GLOSS, map.get("23").palette());
        assertEquals(Palette.GLOSS, map.get("24").palette());
        assertEquals(Palette.GLOSS, map.get("25").palette());
        assertEquals(Palette.GLOSS, map.get("26").palette());
        assertEquals(Palette.GLOSS, map.get("27").palette());
        assertEquals(Palette.GLOSS, map.get("28").palette());
        assertEquals(Palette.GLOSS, map.get("29").palette());
        assertEquals(Palette.GLOSS, map.get("30").palette());
        assertEquals(Palette.GLOSS, map.get("31").palette());
        assertEquals(Palette.GLOSS, map.get("32").palette());
        assertEquals(Palette.GLOSS, map.get("33").palette());
        assertEquals(Palette.GLOSS, map.get("34").palette());
        assertEquals(Palette.GLOSS, map.get("35").palette());
        assertEquals(Palette.GLOSS, map.get("36").palette());
        assertEquals(Palette.GLOSS, map.get("37").palette());
        assertEquals(Palette.GLOSS, map.get("38").palette());
        assertEquals(Palette.GLOSS, map.get("39").palette());
        assertEquals(Palette.GLOSS, map.get("40").palette());
        assertEquals(Palette.GLOSS, map.get("41").palette());
        assertEquals(Palette.GLOSS, map.get("42").palette());
        assertEquals(Palette.GLOSS, map.get("43").palette());
        assertEquals(Palette.GLOSS, map.get("44").palette());
        assertEquals(Palette.GLOSS, map.get("45").palette());
        assertEquals(Palette.GLOSS, map.get("46").palette());
        assertEquals(Palette.GLOSS, map.get("47").palette());
        assertEquals(Palette.GLOSS, map.get("48").palette());
        assertEquals(Palette.GLOSS, map.get("49").palette());
        assertEquals(Palette.GLOSS, map.get("50").palette());
    }
}
