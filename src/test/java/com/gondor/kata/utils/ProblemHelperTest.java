package com.gondor.kata.utils;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.solver.exception.ConstraintViolation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by coding on 18/09/2017.
 */
public class ProblemHelperTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void newCustomerListAfterColorPick() throws Exception {
        Color redWithGloss = new Color("red", Palette.GLOSS);
        Color redWithMatte = new Color("red", Palette.MATTE);

        Color greenWithGloss = new Color("green", Palette.GLOSS);
        Color greenWithMatte = new Color("green", Palette.MATTE);

        Color silverWithGloss = new Color("silver", Palette.GLOSS);

        Customer jack = new Customer("jack", new ArrayList<>(Arrays.asList(redWithGloss, greenWithGloss)));
        Customer tommy = new Customer("tommy", new ArrayList<>(Arrays.asList(redWithMatte, silverWithGloss)));

        List<Customer> customers = new ArrayList<>(Arrays.asList(jack, tommy));

        // remove silver
        List<Customer> modifiedCustomerList = ProblemHelper.newCustomerListAfterColorPick(customers, silverWithGloss);
        assertEquals(1, modifiedCustomerList.size());
        assertEquals(2, modifiedCustomerList.get(0).totalColors());

        // remove redWithMatte
        modifiedCustomerList = ProblemHelper.newCustomerListAfterColorPick(modifiedCustomerList, redWithMatte);
        assertEquals(1, modifiedCustomerList.size());
        assertEquals(1, modifiedCustomerList.get(0).totalColors());
        assertEquals(true, modifiedCustomerList.get(0).colors().get(0).name().equals("green"));

        // remove greenWithMatte
        thrown.expect(ConstraintViolation.class);
        modifiedCustomerList = ProblemHelper.newCustomerListAfterColorPick(modifiedCustomerList, greenWithMatte);
        assertEquals(0, modifiedCustomerList.size());
    }
}