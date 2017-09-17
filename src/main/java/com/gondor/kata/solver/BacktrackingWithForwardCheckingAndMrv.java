package com.gondor.kata.solver;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.solver.exception.ConstraintViolation;
import com.gondor.kata.utils.ProblemHelper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by coding on 18/09/2017.
 */
public class BacktrackingWithForwardCheckingAndMrv extends AbstractSolver {

    public BacktrackingWithForwardCheckingAndMrv(Problem problem) {
        super(problem);
    }

    protected AbstractSolver.Assignment assignColor(Problem problem) {
        // choose a color along with the domain

        List<Customer> customers = problem.customers();
        List<String> colorNames = problem.colorNames();
        Set<Palette> paletteDomains = new LinkedHashSet<>(Arrays.asList(Palette.values()));
        String colorName;

        if (customers.size() == 0) {
            /**
             * if there is no customer, just pick up the color
             */
            colorName = colorNames.remove(0);
        } else {
            List<Customer> sortedCustomers = customers.stream()
                    .sorted(Comparator.comparingInt(Customer::totalColors))
                    .collect(Collectors.toList());

            Customer customer = sortedCustomers.get(0);

            if (customer.totalColors() == 1) {
                // (a) if there is a customer who has only one color
                //      in that case there is no choice other than to choose the color and the palette
                //      to satisfy the customer. At the same time reduce the color domain.
                Color color = customer.colors().get(0);
                colorName = color.name();
                Palette colorPalette = color.palette();
                paletteDomains.retainAll(new HashSet<>(Arrays.asList(colorPalette)));
            } else {
                // (b) else iterate over all the color domains for that color
                Color color = customer.colors().get(0);
                colorName = color.name();
            }
        }

        return new AbstractSolver.Assignment(colorName, paletteDomains.toArray(new Palette[paletteDomains.size()]));
    }

    @Override
    protected List<Customer> customerListAfterColorAssignment(Problem problem, String colorName, Palette domain) throws ConstraintViolation {
        return ProblemHelper.newCustomerListAfterColorPick(problem.customers(), new Color(colorName, domain));
    }
}
