package com.gondor.kata.solver;

import com.gondor.kata.model.Problem;

/**
 * Created by coding on 18/09/2017.
 */
public class BacktrackingWithForwardCheckingAndMrvTest extends AbstractSolverTestHavingLargeInputs {

    @Override
    Solver getSolver(Problem problem) {
        return SolverFactory.backtrackingWithForwardCheckingAndMrv(problem);
    }

}
