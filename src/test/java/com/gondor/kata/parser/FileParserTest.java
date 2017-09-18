package com.gondor.kata.parser;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.parser.exception.ParsingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by coding on 24/08/2017.
 */
public class FileParserTest {

    private Parser fileParser;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        fileParser = new FileParser();
    }

    @Test
    public void canParseValidFile() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("has_solutions/valid_problem_5_colors_3_customers.txt").getFile());

        Problem problem1 = fileParser.parse(file);

        assertEquals(3, problem1.totalCustomers());
        assertEquals(5, problem1.totalColors());
        assertEquals(2, problem1.totalDomains());
    }

    @Test
    public void canLoadCustomerColorAndPalettePreferences() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("has_solutions/valid_problem_2_colors_1_customer.txt").getFile());

        Problem problem = fileParser.parse(file);
        Customer customer = problem.customers().get(0);

        assertEquals(1, problem.totalCustomers());
        assertEquals(2, problem.totalColors());

        // check customer colors
        assertEquals(2, customer.totalColors());
        assertEquals(true, customer.hasColor("1"));
        assertEquals(true, customer.hasColor("2"));
        assertEquals(false, customer.hasColor("3"));
        assertEquals(false, customer.hasColor("4"));

        // check customer color preferences
        assertEquals(true, customer.hasColorWithPalette(new Color("1", Palette.GLOSS)));
        assertEquals(false, customer.hasColorWithPalette(new Color("1", Palette.MATTE)));

        assertEquals(false, customer.hasColorWithPalette(new Color("2", Palette.GLOSS)));
        assertEquals(true, customer.hasColorWithPalette(new Color("2", Palette.MATTE)));

        assertEquals(false, customer.hasColorWithPalette(new Color("3", Palette.GLOSS)));
        assertEquals(false, customer.hasColorWithPalette(new Color("3", Palette.MATTE)));
    }

    @Test
    public void canHandleMissingFile() throws ParsingException {
        File file = new File("missing_file.txt");

        exception.expect(ParsingException.class);
        fileParser.parse(file);
    }

    @Test
    public void canHandleEmptyFile() throws ParsingException {
        File file = new File(getClass().getClassLoader().getResource("invalid_inputs/empty_file.txt").getFile());

        exception.expect(ParsingException.class);
        fileParser.parse(file);
    }

    @Test
    public void canDetectInvalidNumberOfColors() throws ParsingException {
        File file = new File(getClass().getClassLoader().getResource("invalid_inputs/invalid_number_of_colors.txt").getFile());

        exception.expect(ParsingException.class);
        fileParser.parse(file);
    }

    @Test
    public void canDetectInvalidColorName() throws ParsingException {
        File file = new File(getClass().getClassLoader().getResource("invalid_inputs/invalid_color_name.txt").getFile());

        exception.expect(ParsingException.class);
        fileParser.parse(file);
    }

    @Test
    public void canDetectEmptySpace() throws ParsingException {
        /*
          Treating complete empty space in between the customer color choices as ill formatted file
         */
        File file = new File(getClass().getClassLoader()
                .getResource("invalid_inputs/empty_line_in_the_file.txt").getFile());

        exception.expect(ParsingException.class);
        fileParser.parse(file);
    }

    @Test
    public void canDetectCustomersHasNotChosenAllColors() throws ParsingException {
        File file = new File(getClass().getClassLoader()
                .getResource("invalid_inputs/invalid_not_all_colors_are_chosen.txt").getFile());
        exception.expect(ParsingException.class);
        fileParser.parse(file);
    }

    @Test
    public void canHandleSpacesInTheCustomersColorPreference() throws ParsingException {
        File file = new File(getClass().getClassLoader()
                .getResource("has_solutions/valid_problem_3_colors_1_customer_with_empty_space_in_color_choice.txt")
                .getFile());

        Problem problem = fileParser.parse(file);
        Customer customer = problem.customers().get(0);

        assertEquals(1, problem.totalCustomers());
        assertEquals(3, problem.totalColors());

        // check customer colors
        assertEquals(3, customer.totalColors());
        assertEquals(true, customer.hasColor("1"));
        assertEquals(true, customer.hasColor("2"));
        assertEquals(true, customer.hasColor("3"));

        // check customer color preferences
        assertEquals(true, customer.hasColorWithPalette(new Color("1", Palette.GLOSS)));
        assertEquals(true, customer.hasColorWithPalette(new Color("2", Palette.MATTE)));
        assertEquals(true, customer.hasColorWithPalette(new Color("3", Palette.GLOSS)));
    }
}