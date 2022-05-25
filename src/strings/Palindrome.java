package strings;

import java.util.Scanner;

/*
 *
 * Palindrome - The user enters a string and this program checks if the string
 * is a palindrome. That is the string is the same forwards and backwards
 * (ie. "kayak", "racecar", "mom").
 *
 */

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find If The String is a Palindrome!");
        System.out.print("Input the string: ");
        String in = sc.nextLine();
        sc.close();
        String compare = in.toLowerCase().replaceAll(" ", "");
        StringBuilder reversed = new StringBuilder(compare).reverse();
        boolean isPalindrome = compare.equals(reversed.toString());
        System.out.println(isPalindrome ?
                "\"" + in + "\"" + " is a palindrome" :
                "\"" + in + "\"" + " is not a palindrome");
    }
}