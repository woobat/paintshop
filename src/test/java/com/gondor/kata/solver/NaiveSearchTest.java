package com.gondor.kata.solver;

import com.gondor.kata.model.Problem;

/**
 * Created by coding on 17/09/2017.
 */
public class NaiveSearchTest extends AbstractSolverTest {

    @Override
    Solver getSolver(Problem problem) {
        return SolverFactory.naiveSearchSolver(problem);
    }


}