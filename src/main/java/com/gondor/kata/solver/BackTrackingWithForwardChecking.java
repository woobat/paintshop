package com.gondor.kata.solver;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.solver.exception.ConstraintViolation;
import com.gondor.kata.utils.ProblemHelper;

import java.util.List;

/**
 * Created by coding on 18/09/2017.
 */
public class BackTrackingWithForwardChecking extends AbstractSolver {

    public BackTrackingWithForwardChecking(Problem problem) {
        super(problem);
    }

    protected Assignment assignColor(Problem problem) {
        // choose a color along with the domain
        return new Assignment(problem.colorNames().get(0), Palette.values());
    }

    @Override
    protected List<Customer> customerListAfterColorAssignment(Problem problem, String colorName, Palette domain) throws ConstraintViolation {
        return ProblemHelper.newCustomerListAfterColorPick(problem.customers(), new Color(colorName, domain));
    }

}