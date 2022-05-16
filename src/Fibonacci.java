import java.util.Scanner;

/*
 *
 * Fibonacci Sequence - Find the nth number of the fibonacci sequence and the
 * n numbers in the sequence
 *
 */

public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number for Fibonacci Sequence: ");
        int input = sc.nextInt();
        // O(n)
        int[] seq = fibonacciSequence(input);

        System.out.println(input + "th Number of the Fibonacci Sequence is " + seq[input - 1]);

        // O(n)
        for (int num : seq) {
            System.out.print(num + ", ");
        }
    }

    private static int[] fibonacciSequence(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        } else if (n == 1) {
            return new int[]{0};
        } else if (n == 2) {
            return new int[]{0, 1};
        } else {
            int[] cache = new int[n];
            cache[0] = 0;
            cache[1] = 1;
            // O(n)
            for (int i = 2; i < cache.length; i++) {
                cache[i] = cache[i - 1] + cache[i - 2];
            }
            return cache;
        }
    }
}