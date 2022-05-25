package strings;

import java.util.Scanner;

/*
 *
 * ReverseString - The user enters a string and this program outputs the
 * string reversed
 *
 */

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find The Reversed String!");
        System.out.print("Input the string to be reversed: ");
        String in = sc.nextLine();
        sc.close();
        StringBuilder reversed = new StringBuilder(in).reverse();
        System.out.println("The reversed string is \"" + reversed + "\"");
    }
}