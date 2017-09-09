package com.gondor.kata.parser;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.parser.exception.ParsingException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by coding on 09/09/2017.
 */
public class FileParser implements Parser {

    public Problem parse(File file) throws ParsingException {

        List<String> lines;

        try {
            lines = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new ParsingException("error reading file", e);
        }

        int numberOfColors = Integer.parseInt(lines.get(0));

        lines.remove(0);
        int numberOfCustomers = lines.size();
        List<Customer> customers = new ArrayList<>();
        Set<String> colorNames = new HashSet<>();


        for (int i = 0; i < numberOfCustomers; i++) {
            List<Color> colors = new ArrayList<>();

            String customerName = Integer.toString(i + 1);

            String colorChoices = lines.get(i);

            Pattern p = Pattern.compile("\\S+(\\s\\S+)?");
            Matcher m = p.matcher(colorChoices);

            while (m.find()) {

                String[] tokens = m.group(0).split("\\s");
                String colorName = tokens[0];
                String strPalette = tokens[1];

                Palette palette = Palette.from(strPalette);
                System.out.println(m.group());

                Color color = new Color(colorName, palette);
                colors.add(color);

                colorNames.add(colorName);
            }

            Customer newCustomer = new Customer(customerName, colors);
            customers.add(newCustomer);
        }

        return new Problem(customers, new ArrayList<>(colorNames), Arrays.asList(Palette.values()));
    }
}
