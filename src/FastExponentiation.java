import java.util.Scanner;

/*
 *
 * FastExponentiation - The user enters 2 integers a and b and this program
 * outputs the value a^b in O(lgn) time for n = b
 *
 */

public class FastExponentiation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find The Exponentiation of an Integer!");
        System.out.print("Input the base integer: ");
        int base = sc.nextInt();
        System.out.print("Input the exponent as an integer: ");
        int exponent = sc.nextInt();
        int output = power(base, exponent);
        if (exponent < 0) {
            String expInput = "(" + exponent + ")";
            String baseInput = (base < 0 ? "(" + base + ")^" + expInput : base + "^" + expInput);
            System.out.println(baseInput + " = " +
                    (output < 0 ? "-1/" + -1 * output : "1/" + output));
        } else {
            String baseInput = (base < 0 ? "(" + base + ")^" + exponent : base + "^" + exponent);
            System.out.println(baseInput + " = " + output);
        }
    }

    // O(lgn) | T(n) = T(n/2) + O(1)
    private static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else {
            int subPower = power(base, exponent / 2);
            if (exponent % 2 == 0) {
                return subPower * subPower;
            } else {
                return subPower * subPower * base;
            }
        }
    }
}
