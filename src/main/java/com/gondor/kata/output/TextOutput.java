package com.gondor.kata.output;

import com.gondor.kata.config.Constants;
import com.gondor.kata.model.Color;
import com.gondor.kata.model.Solution;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by coding on 18/09/2017.
 */
public class TextOutput implements OutputFormatter {

    private final Optional<Solution> solution;

    public TextOutput(Optional<Solution> solution) {
        this.solution = solution;
    }

    @Override
    public String printResult() {
        if (solution.isPresent()) {
            Map<String, Color> colors = solution.get().colors();
            List<String> keys = colors.keySet()
                    .stream()
                    .mapToInt(Integer::valueOf)
                    .sorted()
                    .boxed()
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            return keys.stream()
                    .reduce("", (rest, key) -> rest + colors.get(key).palette().shortName() + " ")
                    .trim();
        } else {
            return Constants.NO_SOLUTION;
        }
    }
}
