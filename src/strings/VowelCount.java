package strings;

import java.util.Scanner;

/*
 *
 * VowelCount - The user enters a string and this program outputs the
 * number of each vowel and the total number of vowels in the string
 *
 */

public class VowelCount {
    private static final char[] VOWELS = new char[]{'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the Number of Vowels in the String!");
        System.out.print("Input a string: ");
        String in = sc.nextLine().toLowerCase();
        sc.close();
        int[] vowelCount = countVowels(in);
        System.out.print("Vowel Count: ");
        for (int i = 0; i < VOWELS.length; i++) {
            System.out.print(VOWELS[i] + ": " + vowelCount[i] + " | ");
        }
        System.out.println("Total: " + vowelCount[5]);
    }

    private static int[] countVowels(String input) {
        int[] vowelCount = new int[6]; // Sum of all vowels at last index
        for (int strIndex = 0; strIndex < input.length(); strIndex++) {
            for (int vowelIndex = 0; vowelIndex < VOWELS.length; vowelIndex++) {
                if (input.charAt(strIndex) == VOWELS[vowelIndex]) {
                    vowelCount[vowelIndex]++;
                    vowelCount[5]++;
                }
            }
        }
        return vowelCount;
    }
}