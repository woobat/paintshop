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
 * <p>
 * Backtracking based solution which prunes the search space with forward checking.
 */
public class BacktrackingWithForwardChecking extends AbstractSolver {

    public BacktrackingWithForwardChecking(Problem problem) {
        super(problem);
    }

    protected Assignment assignColor(Problem problem) {
        // choose a color along with the domain
        return new Assignment(problem.colorNames().get(0), Palette.values());
    }

    @Override
    protected List<Customer> customerListAfterColorAssignment(Problem problem, String colorName, Palette domain) throws ConstraintViolation {
        /*
        Upon the assignment of this color customer list needs to be updated for example

        - if this assignment satisfies the customer this customer can be removed
        - on the other hand if there is a constraint violation there is no problem to explore further
         */
        return ProblemHelper.newCustomerListAfterColorPick(problem.customers(), new Color(colorName, domain));
    }

}