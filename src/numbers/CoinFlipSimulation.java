package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

/*
 *
 * CoinFlipSimulation - The user continuously flips a fair coin until they
 * decide to stop, and this program outputs the total number of heads and
 * tails and its respective experimental probabilities
 *
 */

public class CoinFlipSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueFlip;
        int trial = 0;
        // outcomes[0] = number of tails flipped | outcomes[1] = number of heads flipped
        int[] outcomes = new int[2];
        System.out.println("Coin Flip Simulation!");
        System.out.print("To Flip the Coin, Enter Anything: ");
        sc.next();
        System.out.println("__________________________________________________");
        do {
            trial++;
            if (flipCoin() == 0) {
                System.out.print("Trial " + trial + ") ");
                System.out.println("The coin flipped TAILS!");
                outcomes[0]++;
            } else {
                System.out.print("Trial " + trial + ") ");
                System.out.println("The coin flipped HEADS!");
                outcomes[1]++;
            }
            System.out.print("To Continue Flipping, Enter \"Y\": ");
            continueFlip = sc.next().equalsIgnoreCase("y");
        } while (continueFlip);
        sc.close();
        int heads = outcomes[1];
        int tails = outcomes[0];
        System.out.println("__________________________________________________");
        System.out.println("Number of Trials: " + trial);
        System.out.println("HEADS Flipped: " + heads + " | TAILS Flipped: " + tails);
        System.out.println("__________________________________________________");
        System.out.println("Experimental Probabilities: ");
        double headProbability = BigDecimal.valueOf((heads * 1.0) / trial)
                .setScale(4, RoundingMode.HALF_UP).doubleValue();
        double tailProbability = BigDecimal.valueOf((tails * 1.0) / trial)
                .setScale(4, RoundingMode.HALF_UP).doubleValue();
        System.out.println("Pr[HEADS] = " + heads + "/" + trial + " = " +
                headProbability + " = " + formatPercent(headProbability));
        System.out.println("Pr[TAILS] = " + tails + "/" + trial + " = " +
                tailProbability + " = " + formatPercent(tailProbability));
        System.out.println("__________________________________________________");
        System.out.println("Theoretical Probabilities: ");
        System.out.println("Pr[HEADS] = Pr[TAILS] = 1/2 = 0.50 = 50%");
        System.out.println("__________________________________________________");
    }

    private static int flipCoin() {
        // 0 = TAILS | 1 = HEADS
        return BigDecimal.valueOf(Math.random()).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    private static String formatPercent(double value) {
        NumberFormat formatter = NumberFormat.getPercentInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(value);
    }
}
