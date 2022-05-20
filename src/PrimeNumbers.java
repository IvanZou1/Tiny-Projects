import java.util.LinkedList;
import java.util.Scanner;

/*
 *
 * PrimeNumbers - Find all prime numbers from 0 to n
 *
 */

public class PrimeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find All Prime Numbers from 0 to n.");
        System.out.print("Input an Integer for n: ");
        int input = sc.nextInt();
        LinkedList<Integer> primes = getPrimes(input);
        print(primes, input);
    }

    private static LinkedList<Integer> getPrimes(int n) {
        LinkedList<Integer> primes = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean isPrime(int val) {
        if (val <= 1) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(val); i++) {
                if (val % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    private static void print(LinkedList<Integer> list, int n) {
        if (list.isEmpty()) {
            System.out.println("There are no prime numbers from 0 to " + n);
        } else {
            System.out.println("The prime numbers from 0 to " + n + ": ");
            int i = 1;
            for (int prime : list) {
                System.out.print(prime + ", ");
                if (i % 15 == 0) {
                    System.out.println();
                }
                i++;
            }
        }
    }
}
