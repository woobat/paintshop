package com.gondor.kata.model;

import java.util.*;

/**
 * Created by coding on 09/09/2017.
 */
public class Problem {

    private final List<Customer> customers;
    private final List<String> colorNames;
    private final List<Palette> colorDomains;

    public Problem(List<Customer> customers, List<String> colorNames, List<Palette> colorDomains) {
        this.customers = customers;
        this.colorNames = colorNames;
        this.colorDomains = colorDomains;
    }

    public List<Customer> customers() {
        return new ArrayList<>(customers);
    }

    public List<String> colorNames() {
        return new ArrayList<>(colorNames);
    }

    public List<Palette> colorDomains() {
        return new ArrayList<>(colorDomains);
    }

    public int totalCustomers() {
        return customers.size();
    }

    public int totalColors() {
        return colorNames.size();
    }

    public int totalDomains() {
        return colorDomains().size();
    }
}

