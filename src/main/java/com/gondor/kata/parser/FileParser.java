package com.gondor.kata.parser;

import com.gondor.kata.model.Color;
import com.gondor.kata.model.Customer;
import com.gondor.kata.model.Palette;
import com.gondor.kata.model.Problem;
import com.gondor.kata.parser.exception.ParsingException;
import com.google.common.base.Preconditions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by coding on 09/09/2017.
 */
public class FileParser implements Parser {

    private List<String> readFile(File file) throws ParsingException {
        List<String> lines;

        try {
            lines = FileUtils.readLines(file, "UTF-8");
            if (lines.size() < 1) {
                throw new ParsingException("Ill-formatted file");
            }
        } catch (IOException e) {
            throw new ParsingException("error reading file", e);
        }
        return lines;
    }

    /**
     * retrieve the total number of colors
     *
     * @param line
     * @throws ParsingException
     */
    private int numberOfColors(String line) throws ParsingException {
        try {
            int numberOfColors = Integer.parseInt(line);
            Preconditions.checkArgument(numberOfColors >= 1, "Argument was %d but expected non-negative", numberOfColors);
            return numberOfColors;
        } catch (IllegalArgumentException e) {
            throw new ParsingException(e);
        }
    }

    /**
     * Parse a given file and ensures it can handle error conditions
     * <p>
     * Acceptable format:
     * 2           (total amount of colors)
     * 1 G         (customer 1 prefers color 1 as {@link Palette#GLOSS})
     * 1 M 2 G     (customer 2 prefers color 1 as {@link Palette#MATTE} and color 2 as {@link Palette#GLOSS})
     *
     * @param file
     * @return
     * @throws ParsingException
     */
    public Problem parse(File file) throws ParsingException {

        List<String> lines;

        lines = readFile(file);

        int totalNumberOfColors = numberOfColors(lines.get(0));
        lines.remove(0);

        int numberOfCustomers = lines.size();
        List<Customer> customers = new ArrayList<>();
        Set<String> colorNames = new HashSet<>();

        for (int i = 0; i < numberOfCustomers; i++) {
            String customerName = Integer.toString(i + 1);
            List<Color> colors = new ArrayList<>();

            String allColorChoicesOfACustomer = lines.get(i);
            if (StringUtils.isBlank(allColorChoicesOfACustomer)) {
                throw new ParsingException("Malformed file - empty line in the middle of a file.");
            }

            Pattern p = Pattern.compile("\\S+(\\s\\S+)?");
            Matcher m = p.matcher(allColorChoicesOfACustomer);

            while (m.find()) {
                String colorPick = m.group(0);
                Color color = parseColorPick(colorPick);
                colors.add(color);
                colorNames.add(color.name());
            }

            Customer newCustomer = new Customer(customerName, colors);
            customers.add(newCustomer);
        }

        validateCustomerColors(totalNumberOfColors, colorNames);

        return new Problem(customers, new ArrayList<>(colorNames), Arrays.asList(Palette.values()));
    }

    /**
     * validate whether totalNumberOfColors specified in the top line of the csv
     * conforms with the color picks by the customer.
     *
     * @param totalNumberOfColors - The first line of the csv specifies how many colors there are
     * @param colorNames          - the color name that is generated at the time of parsing the customer choices
     * @throws ParsingException
     */
    private void validateCustomerColors(int totalNumberOfColors, Set<String> colorNames) throws ParsingException {
        if (colorNames.size() != totalNumberOfColors) {
            // customers are supposed to be choosing from totalNumberOfColors and it should be
            // exactly equal to that. If not there is an issue with that input file.
            // TODO: I think this might be relaxed as there might be couple of cases:
            //      (a) colorNames.size() > totalNumberOfColors (ERROR as customers cannot choose more than the total colors)
            //      (b) colorNames.size() < totalNumberOfColors (I think you should allow this case as there is
            //                                                  no obligation that customer should choose all colors.
            //                                                  Lets leave it for future work.!)
            if (colorNames.size() < totalNumberOfColors)
                throw new ParsingException(String.format("Customers has not chosen all: %d colors", totalNumberOfColors));

            if (colorNames.size() > totalNumberOfColors)
                throw new ParsingException(String.format("Customers cannot choose more than : %d colors", totalNumberOfColors));
        }
    }

    /**
     * build color with chosen palette preference
     *
     * @param colorPick
     * @return
     */
    private Color parseColorPick(String colorPick) throws ParsingException {

        String[] tokens = colorPick.split("\\s");
        if (tokens.length != 2) {
            throw new ParsingException(String.format("Mal-formatted color: %s", colorPick));
        }

        String colorName = tokens[0];
        try {
            Integer.parseInt(colorName);
        } catch (IllegalArgumentException e) {
            throw new ParsingException(String.format("color name should be numeric: %s", colorName));
        }

        String strPalette = tokens[1];
        if (!Palette.isValidShortName(strPalette)) {
            throw new ParsingException(String.format("Palette value is wrong: %s", strPalette));
        }

        Palette palette = Palette.fromShortName(strPalette);
        return new Color(colorName, palette);
    }

}
