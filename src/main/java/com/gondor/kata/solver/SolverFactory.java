package com.gondor.kata.solver;

import com.gondor.kata.model.Problem;
import com.gondor.kata.model.Strategy;

/**
 * Created by coding on 18/09/2017.
 */
public class SolverFactory {

    public static Solver solver(Strategy strategy, Problem problem) {

        if (strategy.equals(Strategy.NAIVE_SEARCH)) {
            return naiveSearchSolver(problem);
        } else if (strategy.equals((Strategy.BACKTRACKING_WITH_FORWARD_CHECKING))) {
            return backtrackingWithForwardChecking(problem);
        } else {
            return backtrackingWithForwardCheckingAndMrv(problem);
        }
    }

    public static Solver naiveSearchSolver(Problem problem) {
        return new NaiveSearch(problem);
    }

    public static Solver backtrackingWithForwardChecking(Problem problem) {
        return new BacktrackingWithForwardChecking(problem);
    }

    public static Solver backtrackingWithForwardCheckingAndMrv(Problem problem) {
        return new BacktrackingWithForwardCheckingAndMrv(problem);
    }

}
