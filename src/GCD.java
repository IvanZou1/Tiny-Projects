import java.util.Scanner;

/*
 *
 * GCD - Find the greatest common denominator between two integers using
 * Euclid's Algorithm
 *
 */

public class GCD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find The Greatest Common Denominator Between Two Integers!");
        System.out.println("Input Two Integers:");
        int in1 = sc.nextInt();
        int in2 = sc.nextInt();
        int gcd = findGCD(in1, in2);
        System.out.println("The Greatest Common Denominator of " +
                in1 + " and " + in2 + " is " + gcd);
        sc.close();
    }

    // Euclid's Algorithm
    // O(log(num2))
    private static int findGCD(int num1, int num2) {
        // Make negative integer inputs positive
        int a = (num1 < 0 ? -1 * num1 : num1);
        int b = (num2 < 0 ? -1 * num2 : num2);
        if (num2 == 0) {
            return num1;
        } else {
            int c = a % b;
            return findGCD(b, c);
        }
    }
}
