package numbers;

import java.util.Scanner;

/*
 *
 * Euler's Number - Find Euler's Number (e) to the nth decimal with a limit of
 * 60 decimal places
 *
 */

public class EulerNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find Euler's Number to the nth decimal!");
        System.out.print("Input a positive number for n: ");
        int input = sc.nextInt();
        if (input < 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Positive Number");
        } else if (input > 60) {
            System.out.println("Limit Exceeded! Input a smaller positive integer");
        } else if (input == 0) {
            System.out.println("Euler's Number to the 0th decimal:");
            System.out.println("2");
        } else {
            System.out.println("Euler's Number to the " + input + "th decimal:");
            System.out.println(findEulers(input));
        }
    }

    private static String findEulers(int n) {
        String e = "2.718281828459045235360287471352662497757247093699959574966967";
        return new StringBuilder(e).delete(n + 2, e.length()).toString();
    }
}
