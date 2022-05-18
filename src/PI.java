import java.util.Scanner;

/*
 *
 * PI - Find PI to the nth decimal with a limit of 60 decimal places
 *
 */

public class PI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find PI to the nth decimal!");
        System.out.print("Input a positive number for n: ");
        int input = sc.nextInt();
        if (input < 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Positive Number");
        } else if (input > 60) {
            System.out.println("Limit Exceeded! Input a smaller positive integer");
        } else if (input == 0) {
            System.out.println("PI to the 0th decimal:");
            System.out.println("3");
        } else {
            System.out.println("PI to the " + input + "th decimal:");
            System.out.println(findPI(input));
        }
    }

    private static String findPI(int n) {
        String pi = "3.141592653589793238462643383279502884197169399375105820974944";
        return new StringBuilder(pi).delete(n + 2, pi.length()).toString();
    }
}
