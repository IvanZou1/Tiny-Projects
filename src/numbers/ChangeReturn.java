package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/*
 *
 * ChangeReturn - The users enters a cost and the amount of money given, and this
 * program outputs the amount of change in common bills and coins.
 *
 */

public class ChangeReturn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the amount of change for your purchase!");
        System.out.print("Input the total cost: $");
        BigDecimal cost = sc.nextBigDecimal();
        cost = cost.setScale(2, RoundingMode.HALF_UP);
        checkIfInvalid(cost);
        System.out.print("Input the amount of your payment: $");
        BigDecimal pay = sc.nextBigDecimal();
        sc.close();
        pay = pay.setScale(2, RoundingMode.HALF_UP);
        checkIfInvalid(pay);
        if (cost.compareTo(pay) > 0) {
            System.out.println("Insufficient payment for this purchase!");
            double owe = cost.subtract(pay).doubleValue();
            System.out.println("You owe " + formatMoney(owe));
        } else if (cost.compareTo(pay) == 0) {
            System.out.println("No Change");
        } else {
            BigDecimal change = pay.subtract(cost);
            int[] billsAndCoins = getChangeArray(change);
            System.out.println("Your Change is " + arrayOfChangeToString(billsAndCoins));
        }
    }

    private static void checkIfInvalid(BigDecimal input) {
        if (input.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Non-Negative Number");
        }
    }

    private static String formatMoney(double money) {
        Locale us = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(us);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setGroupingUsed(true);
        return formatter.format(money);
    }

    private static int[] getChangeArray(BigDecimal change) {
        double[] moneyValues = new double[]{100, 50, 20, 10, 5, 1, 0.25, 0.10, 0.05, 0.01};
        int[] billsAndCoins = new int[moneyValues.length];
        BigDecimal currChange = change;
        for (int i = 0; i < moneyValues.length; i++) {
            billsAndCoins[i] = getNumOfBillsOrCoins(currChange, moneyValues[i]);
            currChange = currChange.remainder(BigDecimal.valueOf(moneyValues[i]));
        }
        return billsAndCoins;
    }

    private static int getNumOfBillsOrCoins(BigDecimal change, double amount) {
        return change.divide(BigDecimal.valueOf(amount), RoundingMode.DOWN).intValue();
    }

    private static String arrayOfChangeToString(int[] changeArray) {
        String[] moneyStrings = new String
                []{"$100", "$50", "$20", "$10", "$5", "$1", "quarter", "dime", "nickel", "penny"};
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < changeArray.length; i++) {
            if (i <= 5) {
                stringHelperBills(changeArray[i], moneyStrings[i], str);
            } else {
                stringHelperCoins(changeArray[i], moneyStrings[i], str);
            }
        }
        return str.toString();
    }

    private static void stringHelperBills(int count, String billType, StringBuilder str) {
        if (count != 0) {
            str.append(formatNumber(count));
            str.append(" ");
            str.append(billType);
            str.append(count == 1 ? " Bill, " : " Bills, ");
        }
    }

    private static String formatNumber(int count) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        formatter.setGroupingUsed(true);
        return formatter.format(count);
    }

    private static void stringHelperCoins(int count, String coinType, StringBuilder str) {
        if (count != 0) {
            str.append(count);
            str.append(" ");
            if (!coinType.equals("penny")) {
                str.append(coinType);
                str.append(count == 1 ? ", " : "s, ");
            } else {
                str.append(count == 1 ? "penny" : "pennies");
            }
        }
    }
}