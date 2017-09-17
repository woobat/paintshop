package com.gondor.kata.solver;

import com.gondor.kata.model.Solution;

import java.util.Optional;

/**
 * Created by coding on 17/09/2017.
 */
public interface Solver {

    void solve();

    Optional<Solution> solution();

    boolean isSolvable();
}
