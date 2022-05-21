package numbers;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Scanner;

/*
 *
 * EnglishNumerals - The user enters a number, and this program outputs the
 * number expressed in plain English words.
 *
 * This program support inputs up to Integer.MAX_VALUE
 *
 * Ex -
 * 3 -> Three
 * 15 -> Fifteen
 * 105 -> One Hundred Five
 * 2550 -> Two Thousand Five Hundred Fifty
 * etc.
 *
 */

public class EnglishNumerals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("English Numerals!");
        System.out.print("Input an Integer: ");
        int input = sc.nextInt();
        LinkedList<String> numberWords = getNumberWords(input);
        System.out.println(formatNumber(input) + " in English Words: " + listToString(numberWords));
    }

    private static LinkedList<String> getNumberWords(int number) {
        String[] digits = new String[]{
                "Zero", "One", "Two", "Three", "Four",
                "Five", "Six", "Seven", "Eight", "Nine"};
        String[] teens = new String[]{
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
                "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = new String[]{
                "Ten", "Twenty", "Thirty", "Forty", "Fifty",
                "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] rank = new String[]{"Hundred", "Thousand", "Million", "Billion"};
        boolean isNegative = number < 0;
        number = isNegative ? -1 * number : number;
        String numStr = String.valueOf(number);
        LinkedList<String> numberWords = new LinkedList<>();
        for (int i = 0; i < numStr.length(); i++) {
            int digitPlace = numStr.length() - i;
            int currDigit = Integer.parseInt(String.valueOf(numStr.charAt(i)));
            String currDigitWord = digits[currDigit];
            boolean existPrevDigit = exist(i - 1, numStr);
            int prevDigit =  existPrevDigit ?
                    Integer.parseInt(String.valueOf(numStr.charAt(i - 1))) : -1;
            if (digitPlace % 3 == 0) {
                if (existPrevDigit && prevThreeDigitsNotZero(i, numStr)) {
                    if (digitPlace == 3) {
                        numberWords.add(rank[1]);
                    } else if (digitPlace == 6) {
                        numberWords.add(rank[2]);
                    } else if (digitPlace == 9) {
                        numberWords.add(rank[3]);
                    }
                }
                if (currDigit != 0) {
                    numberWords.add(currDigitWord);
                    numberWords.add(rank[0]);
                }
            } else if (digitPlace == 10 || digitPlace == 7 || digitPlace == 4 || digitPlace == 1) {
                if (!existPrevDigit || (prevDigit == 0 && currDigit != 0)) {
                    numberWords.add(currDigitWord);
                } else {
                    if (prevDigit == 1) {
                        numberWords.add(teens[currDigit]);
                    } else if (prevDigit > 1 && prevDigit < 10) {
                        if (currDigit != 0) {
                            numberWords.add(tens[prevDigit - 1] + "-" + currDigitWord);
                        } else {
                            numberWords.add(tens[prevDigit - 1]);
                        }
                    }
                }
            }
        }
        if (isNegative) {
            numberWords.addFirst("Negative");
        }
        return numberWords;
    }

    private static boolean exist(int index, String number) {
        return index >= 0 && index < number.length();
    }

    private static boolean prevThreeDigitsNotZero(int index, String number) {
        for (int i = 1; i <= 3; i++) {
            int prev = index - i;
            if (exist(prev, number)) {
                if (number.charAt(prev) != '0') {
                    return true;
                }
            }
        }
        return false;
    }

    private static String formatNumber(int count) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        formatter.setGroupingUsed(true);
        return formatter.format(count);
    }

    private static String listToString(LinkedList<String> words) {
        StringBuilder str = new StringBuilder();
        for (String word : words) {
            str.append(word);
            str.append(" ");
        }
        return str.toString();
    }
}
