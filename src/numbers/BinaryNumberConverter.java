package numbers;

import java.util.Scanner;

/*
 *
 * BinaryNumberConverter - The users chooses to either convert a binary number
 * to the decimal number system or a decimal number to a binary number, then
 * the user enters the respective number they want to convert, and this program
 * outputs the conversion
 *
 */

public class BinaryNumberConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Binary Number Converter!");
        System.out.print("Convert Binary Number to Decimal Number (Enter 0) | ");
        System.out.println("Convert Decimal Number to Binary Number (Enter 1)");
        int converter = sc.nextInt();
        if (converter == 0) {
            System.out.println("Binary Number to Decimal Number Conversion:");
            System.out.print("Enter a Binary Number: ");
            String binary = sc.next();
            checkIfInvalidBinary(binary);
            System.out.println(binary + " to decimal is " + binaryToDecimal(binary));
        } else if (converter == 1) {
            System.out.println("Decimal Number to Binary Number Conversion:");
            System.out.print("Enter the Decimal Number as a Positive Integer: ");
            int decimal = sc.nextInt();
            checkIfInvalidDecimal(decimal);
            System.out.println(decimal + " to binary is " + decimalToBinary(decimal));
        } else {
            throw new IllegalArgumentException("Invalid Input: Please input 0 or 1");
        }
    }

    private static void checkIfInvalidBinary(String binary) {
        if (Long.parseLong(binary) < 0) {
            throw new IllegalArgumentException("Invalid Input: Binary Numbers Can Not be Negative");
        } else {
            for (int i = 0; i < binary.length(); i++) {
                char currBit = binary.charAt(i);
                if (currBit != '0') {
                    if (currBit != '1') {
                        throw new IllegalArgumentException("Invalid Input: Input is Not Binary");
                    }
                }
            }
        }
    }

    private static int binaryToDecimal(String binary) {
        int decimal = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            char currBit = binary.charAt(i);
            if (currBit == '1') {
                decimal += Math.pow(2, binary.length() - i - 1);
            }
        }
        return decimal;
    }

    private static void checkIfInvalidDecimal(int decimal) {
        if (decimal < 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Positive Integer");
        }
    }

    private static String decimalToBinary(int decimal) {
        StringBuilder binaryStr = new StringBuilder();
        if (decimal == 0) {
            return "0";
        }
        while (decimal > 0) {
            binaryStr.insert(0, decimal % 2);
            decimal /= 2;
        }
        return binaryStr.toString();
    }
}
