import java.util.LinkedList;
import java.util.Scanner;

/*
 *
 * PrimeFactorization - Find all prime factors of an integer n in O(n)
 *
 */

public class PrimeFactorization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find All Prime Factors of an Integer!");
        System.out.print("Input an Integer: ");
        int input = sc.nextInt();
        int num = input < 0 ? input * -1 : input;
        LinkedList<Integer> primeFactors = getPrimeFactors(num);
        print(primeFactors, input);
    }

    // O(n)
    private static LinkedList<Integer> getPrimeFactors(int n) {
        LinkedList<Integer> primeFactors = new LinkedList<>();
        int factor = 2;
        while (n > 1) {
            if (n % factor == 0) {
                primeFactors.add(factor);
                n /= factor;
            } else {
                factor++;
            }
        }
        return primeFactors;
    }

    private static void print(LinkedList<Integer> list, int n) {
        if (list.isEmpty()) {
            System.out.println(n + " has no prime factors!");
        } else {
            System.out.print("The prime factors of " + n + ": ");
            for (int prime : list) {
                System.out.print(prime + ", ");
            }
        }
    }
}
