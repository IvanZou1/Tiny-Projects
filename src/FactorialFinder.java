import java.util.Scanner;

/*
 *
 * FactorialFinder - Find the nth factorial using both loops and recursion
 *
 */

public class FactorialFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the Factorial!");
        System.out.print("Input a positive number: ");
        int input = sc.nextInt();
        checkIfInvalidInput(input);
        System.out.println("Loop: " + input + "! = " + loopFactorial(input));
        System.out.println("Recursion: " + input + "! = " + recursiveFactorial(input));
    }

    private static void checkIfInvalidInput(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Positive Number");
        }
    }

    private static int loopFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            int factorial = 1;
            for (int i = 1; i <= n; i++) {
                factorial *= i;
            }
            return factorial;
        }
    }

    private static int recursiveFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return recursiveFactorial(n - 1) * n;
        }
    }
}
