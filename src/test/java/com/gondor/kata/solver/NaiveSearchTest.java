package com.gondor.kata.solver;

import com.gondor.kata.model.Problem;

import static org.junit.Assert.*;

/**
 * Created by coding on 17/09/2017.
 */
public class NaiveSearchTest extends AbstractSolverTest {

    @Override
    Solver getSolver(Problem problem) {
        return new NaiveSearch(problem);
    }

}
