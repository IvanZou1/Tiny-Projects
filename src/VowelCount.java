import java.util.Scanner;

/*
 *
 * VowelCount - The user enters a string and this program outputs the
 * number of each vowel and the total number of vowels in the string
 *
 */

public class VowelCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the Number of Vowels in the String!");
        System.out.print("Input a string: ");
        String in = sc.nextLine().toLowerCase();
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        int[] vowelCount = countVowels(in, vowels);
        System.out.print("Vowel Count: ");
        for (int i = 0; i < vowels.length; i++) {
            System.out.print(vowels[i] + ": " + vowelCount[i] + " | ");
        }
        System.out.println("Total: " + vowelCount[5]);
    }

    private static int[] countVowels(String input, char[] vowels) {
        int[] vowelCount = new int[6]; // Sum of all vowels at last index
        for (int strIndex = 0; strIndex < input.length(); strIndex++) {
            for (int vowelIndex = 0; vowelIndex < vowels.length; vowelIndex++) {
                if (input.charAt(strIndex) == vowels[vowelIndex]) {
                    vowelCount[vowelIndex]++;
                    vowelCount[5]++;
                }
            }
        }
        return vowelCount;
    }
}