package com.gondor.kata.solver;

import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.solver.exception.ConstraintViolation;

import java.util.List;

/**
 * Created by coding on 17/09/2017.
 *
 * Systematically explore the search space by traversing each color and domain and finally pick the solution
 * that will have minimum number of *mattes* to satisfy all the customers.
 */
public class NaiveSearch extends AbstractSolver {

    public NaiveSearch(Problem problem) {
        super(problem);
    }

    protected Assignment assignColor(Problem problem) {
        // assign a color
        return new Assignment(problem.colorNames().get(0), Palette.values());
    }

    @Override
    protected List<Customer> customerListAfterColorAssignment(Problem problem, String colorName, Palette domain) throws ConstraintViolation {
        return problem.customers();
    }
}
