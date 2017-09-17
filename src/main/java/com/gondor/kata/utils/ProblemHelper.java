package com.gondor.kata.utils;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Customer;
import com.gondor.kata.solver.exception.ConstraintViolation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coding on 18/09/2017.
 */
public class ProblemHelper {

    public static List<Customer> newCustomerListAfterColorPick(List<Customer> customers, Color color) throws ConstraintViolation {

        List<Customer> newCustomerList = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.hasColor(color.name())) {
                if (!customer.hasColorWithPalette(color)) {
                    // if this customer does not have this color with expected palette then he is not satisfied
                    if (customer.totalColors() == 1) {
                        // if he has only one color and the preference does not match then there
                        // is no way we can satisfy him....
                        throw new ConstraintViolation(
                                String.format("cannot satisfy constraint for customer=%s having color(name=%s,palette=%s)"
                                        , customer.name(), color.name(), color.palette()));
                    }
                    newCustomerList.add(Customer.removeColor(customer, color.name()));
                }
            } else {
                newCustomerList.add(Customer.copyOf(customer));
            }
        }

        return newCustomerList;
    }

}
