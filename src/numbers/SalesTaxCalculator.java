package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

/*
 *
 * SalesTaxCalculator - The user enters a cost and then either enters the
 * USPS abbreviation of a U.S. state (ie. NY, CA, PA, etc.) or a tax rate,
 * and this program outputs the tax and the total cost with the tax.
 *
 * The state taxes in this program are as of 6/01/2022.
 *
 */

public class SalesTaxCalculator {
    private static final HashMap<String, Double> STATE_TAXES = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("US State Sales Tax Calculator!");
        System.out.print("Enter the Cost: ");
        BigDecimal cost = sc.nextBigDecimal();
        cost = cost.setScale(2, RoundingMode.HALF_UP);
        System.out.print("Enter the USPS Abbreviation of a State or a Tax Rate: ");
        String input = sc.next().toUpperCase();
        BigDecimal taxRate = null;
        String taxRateStr = "";
        initializeStateTaxesMap();
        if (isAbbreviation(input)) {
            taxRate = BigDecimal.valueOf(STATE_TAXES.get(input));
            taxRateStr = formatPercent(taxRate.doubleValue()) + " (" + input + ")";
        } else if (isPercentage(input)) {
            taxRate = BigDecimal.valueOf(Double.parseDouble(
                    input.substring(0, input.length() - 1)) / 100.0);
            taxRateStr = formatPercent(taxRate.doubleValue());
        } else if (isDecimal(input)) {
            taxRate = BigDecimal.valueOf(Double.parseDouble(input));
            taxRateStr = formatPercent(taxRate.doubleValue());
        }
        BigDecimal tax = cost.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = cost.add(tax).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Cost: " + formatMoney(cost.doubleValue()) +
                " | Tax Rate: " + taxRateStr);
        System.out.println("Tax: " + formatMoney(tax.doubleValue()));
        System.out.println("Total Cost: " + formatMoney(total.doubleValue()));
    }

    private static void initializeStateTaxesMap() {
        STATE_TAXES.put("AL", 0.0400); // Alabama
        STATE_TAXES.put("AK", 0.0000); // Alaska
        STATE_TAXES.put("AZ", 0.0560); // Arizona
        STATE_TAXES.put("AR", 0.0650); // Arkansas
        STATE_TAXES.put("CA", 0.0725); // California
        STATE_TAXES.put("CO", 0.0290); // Colorado
        STATE_TAXES.put("CT", 0.0635); // Connecticut
        STATE_TAXES.put("DE", 0.0000); // Delaware
        STATE_TAXES.put("FL", 0.0600); // Florida
        STATE_TAXES.put("GA", 0.0400); // Georgia

        STATE_TAXES.put("HI", 0.0400); // Hawaii
        STATE_TAXES.put("ID", 0.0600); // Idaho
        STATE_TAXES.put("IL", 0.0625); // Illinois
        STATE_TAXES.put("IN", 0.0700); // Indiana
        STATE_TAXES.put("IA", 0.0600); // Iowa
        STATE_TAXES.put("KS", 0.0650); // Kansas
        STATE_TAXES.put("KY", 0.0600); // Kentucky
        STATE_TAXES.put("LA", 0.0445); // Louisiana
        STATE_TAXES.put("ME", 0.0550); // Maine
        STATE_TAXES.put("MD", 0.0600); // Maryland

        STATE_TAXES.put("MA", 0.0625); // Massachusetts
        STATE_TAXES.put("MI", 0.0600); // Michigan
        STATE_TAXES.put("MN", 0.06875); // Minnesota
        STATE_TAXES.put("MS", 0.0700); // Mississippi
        STATE_TAXES.put("MO", 0.04225); // Missouri
        STATE_TAXES.put("MT", 0.0000); // Montana
        STATE_TAXES.put("NE", 0.0550); // Nebraska
        STATE_TAXES.put("NV", 0.0685); // Nevada
        STATE_TAXES.put("NH", 0.0000); // New Hampshire
        STATE_TAXES.put("NJ", 0.06625); // New Jersey

        STATE_TAXES.put("NM", 0.05125); // New Mexico
        STATE_TAXES.put("NY", 0.0400); // New York
        STATE_TAXES.put("NC", 0.0475); // North Carolina
        STATE_TAXES.put("ND", 0.0500); // North Dakota
        STATE_TAXES.put("OH", 0.0575); // Ohio
        STATE_TAXES.put("OK", 0.0450); // Oklahoma
        STATE_TAXES.put("OR", 0.0000); // Oregon
        STATE_TAXES.put("PA", 0.0600); // Pennsylvania
        STATE_TAXES.put("RI", 0.0700); // Rhode Island
        STATE_TAXES.put("SC", 0.0600); // South Carolina

        STATE_TAXES.put("SD", 0.0450); // South Dakota
        STATE_TAXES.put("TN", 0.0700); // Tennessee
        STATE_TAXES.put("TX", 0.0625); // Texas
        STATE_TAXES.put("UT", 0.0485); // Utah
        STATE_TAXES.put("VT", 0.0600); // Vermont
        STATE_TAXES.put("VA", 0.0430); // Virginia
        STATE_TAXES.put("WA", 0.0650); // Washington
        STATE_TAXES.put("WV", 0.0600); // West Virginia
        STATE_TAXES.put("WI", 0.0500); // Wisconsin
        STATE_TAXES.put("WY", 0.0400); // Wyoming

        STATE_TAXES.put("PR", 0.1150); // Puerto Rico
        STATE_TAXES.put("DC", 0.0600); // District of Columbia
    }

    private static boolean isAbbreviation(String input) {
        return STATE_TAXES.containsKey(input);
    }

    private static boolean isPercentage(String input) {
        for (int i = 0; i < input.length() - 1; i++) {
            char curr = input.charAt(i);
            if (!Character.isDigit(curr)) {
                if (curr != '.') {
                    throw new IllegalArgumentException("Invalid Input");
                }
            }
        }
        return input.charAt(input.length() - 1) == '%';
    }

    private static String formatPercent(double value) {
        NumberFormat formatter = NumberFormat.getPercentInstance();
        formatter.setMaximumFractionDigits(3);
        formatter.setMinimumFractionDigits(3);
        return formatter.format(value);
    }

    private static boolean isDecimal(String input) {
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (!Character.isDigit(curr)) {
                if (curr != '.') {
                    throw new IllegalArgumentException("Invalid Input");
                }
            }
        }
        return true;
    }

    private static String formatMoney(double money) {
        Locale us = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(us);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setGroupingUsed(true);
        return formatter.format(money);
    }
}
