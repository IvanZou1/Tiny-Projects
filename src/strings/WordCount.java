package strings;

import java.util.Scanner;

/*
 *
 * WordCount - The user enters a string and this program outputs the total
 * number of words, character, and characters excluding spaces in the string
 *
 * This program defines a "word" as a string made up at least one letter or
 * digit, and each word is separated with a space.
 * Ex -
 * Single Words: "123", "abc", "a1b2c3", "hey!", "50%", "Spider-Man!", "2/3", etc.
 * Not Words: "%%%", "     ", "@#!$", "{$#}", "?/?", "<\>", etc.
 *
 */

public class WordCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the Number of Vowels in the String!");
        System.out.print("Input a string: ");
        String in = sc.nextLine();
        System.out.println("Word Count: ");
        int[] wordCount = countWords(in);
        System.out.println("Words: " + wordCount[0]);
        System.out.println("Characters: " + wordCount[1]);
        System.out.println("Characters (excluding spaces): " + wordCount[2]);
    }

    private static int[] countWords(String input) {
        /*
         * wordCount[0] - number of words
         * wordCount[1] - number of characters
         * wordCount[2] - number of characters excluding spaces
         */
        int[] wordCount = new int[3];
        for (int i = 0; i < input.length(); i++) {
            wordCount[1]++;
            if (!Character.isWhitespace(input.charAt(i))) {
                wordCount[2]++;
            }
        }
        String[] possibleWords = input.split(" ");
        for (String possibleWord : possibleWords) {
            for (int j = 0; j < possibleWord.length(); j++) {
                if (Character.isLetterOrDigit(possibleWord.charAt(j))) {
                    wordCount[0]++;
                    break;
                }
            }
        }
        return wordCount;
    }
}