package numbers;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/*
 *
 * MortgageCalculator - The user enters a loan amount, the fixed loan term
 * (years), and the annual interest rate (%), and this program outputs the
 * total amount owed and the monthly payment
 *
 */

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The Mortgage Calculator!");
        System.out.print("Enter the Loan Amount: $");
        double loan = sc.nextDouble();
        checkIfInvalid(loan);
        System.out.print("Enter the Fixed Loan Term (Years): ");
        int term = sc.nextInt();
        checkIfInvalid(term);
        System.out.print("Enter the Annual Interest Rate (%): ");
        double interestRate = sc.nextDouble();
        checkIfInvalid(interestRate);
        double monthlyPayments = calculateMonthlyPayments(loan, interestRate, term);
        double owed = calculateTotalOwed(monthlyPayments, term);
        System.out.println("The Amount Owed to the Bank: " + formatMoney(owed));
        System.out.println("The Minimum Monthly Payment: " + formatMoney(monthlyPayments));
    }

    private static void checkIfInvalid(double input) {
        if (input < 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Non-Negative Number");
        }
    }

    private static void checkIfInvalid(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Positive Number");
        }
    }

    private static double calculateMonthlyPayments(double loan,
                                                       double interest, int term) {
        int totalMonths = 12 * term;
        if (interest == 0) {
            return loan / totalMonths;
        } else {
            double monthlyRate = (interest / 100) / 12;
            double base = monthlyRate + 1;
            double basePow = Math.pow(base, totalMonths);
            return (loan * monthlyRate * basePow) / (basePow - 1);
        }
    }

    private static double calculateTotalOwed(double monthlyPayments, int term) {
        int totalMonths = 12 * term;
        return monthlyPayments * totalMonths;
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
