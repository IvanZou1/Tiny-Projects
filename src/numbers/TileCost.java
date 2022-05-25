package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/*
 *
 * TileCost - The user enters the length (L) and width (W) of a floor plan
 * and the length and cost of a single square tile, and this program outputs the
 * total cost of tiles to fill the L by W floor and the number of tiles needed
 *
 */

public class TileCost {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the Total Cost of Filling a L by W Floor with Tiles!");
        System.out.println("Enter the Dimensions of the Floor: ");
        System.out.print("Length (L): ");
        BigDecimal length = sc.nextBigDecimal();
        checkIfInvalid(length);
        System.out.print("Width (W): ");
        BigDecimal width = sc.nextBigDecimal();
        checkIfInvalid(width);
        System.out.print("Enter the Length of the Square Tile: ");
        BigDecimal tileLength = sc.nextBigDecimal();
        checkIfInvalid(tileLength);
        System.out.print("Enter the Cost of One Tile: $");
        BigDecimal tileCost = sc.nextBigDecimal();
        sc.close();
        tileCost = tileCost.setScale(2, RoundingMode.UP);
        checkIfInvalid(tileCost);
        double totalCost = calculateTotalCost(length, width, tileLength, tileCost);
        int totalTiles = calculateTotalTiles(length, width, tileLength);
        System.out.println("The Total Cost: ~" + formatMoney(totalCost));
        System.out.print("The Total Number of Tiles Need: ~" + formatNumber(totalTiles));
    }

    private static void checkIfInvalid(BigDecimal input) {
        if (input.doubleValue() <= 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Positive Number");
        }
    }

    private static double calculateTotalCost(BigDecimal fLen, BigDecimal fWid,
                                             BigDecimal tLen, BigDecimal tCost) {
        int totalTiles = calculateTotalTiles(fLen, fWid, tLen);
        return BigDecimal.valueOf(totalTiles).multiply(tCost).
                setScale(2, RoundingMode.UP).doubleValue();
    }

    private static int calculateTotalTiles(BigDecimal fLen, BigDecimal fWid,
                                           BigDecimal tLen) {
        BigDecimal floorArea = fLen.multiply(fWid);
        BigDecimal tileArea = tLen.pow(2);
        return floorArea.divide(tileArea, RoundingMode.UP).intValue();
    }

    private static String formatMoney(double money) {
        Locale us = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(us);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setGroupingUsed(true);
        return formatter.format(money);
    }

    private static String formatNumber(int num) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        formatter.setGroupingUsed(true);
        return formatter.format(num);
    }
}
