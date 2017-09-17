package com.gondor.kata.solver;

import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.model.Solution;
import com.gondor.kata.solver.exception.ConstraintViolation;

import java.util.*;

/**
 * Created by coding on 17/09/2017.
 */
public abstract class AbstractSolver implements Solver {

    private Problem problem;
    private List<Solution> solutions;
    private boolean isSolvable;

    public AbstractSolver(Problem problem) {
        this.problem = problem;
        this.solutions = new ArrayList<>();
        this.isSolvable = false;
    }

    protected abstract List<Customer> customerListAfterColorAssignment(Problem problem, String colorName, Palette domain) throws ConstraintViolation;

    protected abstract Assignment assignColor(Problem problem);

    @Override
    public Optional<Solution> solution() {

        Comparator<Solution> sortedSolutions = Comparator.comparingInt(Solution::totalMattes);
        solutions.sort(sortedSolutions);

        Solution result = null;
        if (solutions.size() >= 1)
            result = solutions.get(0);

        return Optional.ofNullable(result);
    }

    @Override
    public boolean isSolvable() {
        return isSolvable;
    }

    @Override
    public void solve() {
        solve(this.problem,
                new Solution(problem.totalColors(), problem.customers()));
    }

    protected static class Assignment {
        private final String colorName;
        private final Palette[] domains;

        Assignment(String colorName, Palette[] domains) {
            this.colorName = colorName;
            this.domains = domains;
        }
    }

    private boolean solve(Problem problem, Solution solution) {

        if (solution.isLeafNode()) {
            if (solution.isGoalNode()) {
                solutions.add(solution);
                isSolvable = true;
                return true;
            } else {
                return false;
            }
        }

        List<String> colorNames = problem.colorNames();

        // exhausted search space and no solution found
        if (colorNames.size() == 0) {
            return false;
        }

        Assignment assignedColor = assignColor(problem);
        String colorName = assignedColor.colorName;
        Palette[] domains = assignedColor.domains;

        colorNames.remove(colorName);

        for (Palette domain : domains) {
            try {
                List<Customer> newCustomerList = customerListAfterColorAssignment(problem, colorName, domain);

                Problem subProblem = new Problem(newCustomerList,
                        new ArrayList<>(colorNames),
                        Arrays.asList(Palette.values()));

                Solution copy = solution.copy();
                copy.addColor(colorName, domain);

                solve(subProblem, copy);
            } catch (ConstraintViolation constraintViolation) {
                // if there is a Constraint Violation there is no point to further search in that subtree
                // System.out.println(constraintViolation.getMessage());
            }
        }

        return false;
    }

}