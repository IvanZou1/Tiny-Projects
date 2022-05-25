package numbers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/*
 *
 * HappyNumbers - Find the next five "happy numbers" given a starting number.
 * A "happy number" is defined as a number that will eventually equal 1 when
 * continuously replaced by the sum of the squares of each digit.
 *
 * Ex -
 * 10 is a happy number because 1^2 + 0^2 = 1 + 0 = 1
 * 13 is a happy number because 1^2 + 3^2 = 1 + 9 = 10 -> 1^2 + 0^2 = 1 + 0 = 1
 * 31 is a happy number because 3^2 + 1^2 = 9 + 1 = 10 -> 1^2 + 0^2 = 1 + 0 = 1
 * etc.
 *
 */

public class HappyNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Happy Numbers!");
        System.out.print("Enter a Positive Number as a Starting Number: ");
        int start = sc.nextInt();
        sc.close();
        checkIfInvalid(start);
        LinkedList<Integer> happyNumbers = getFiveHappyNumbers(start);
        printList(happyNumbers, start);
    }

    private static void checkIfInvalid(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("Invalid Input: Please Enter a Positive Number");
        }
    }

    private static LinkedList<Integer> getFiveHappyNumbers(int start) {
        LinkedList<Integer> happyNumbers = new LinkedList<>();
        while (happyNumbers.size() != 5) {
            if (isHappy(start)) {
                happyNumbers.add(start);
            }
            start++;
        }
        return happyNumbers;
    }

    private static boolean isHappy(int number) {
        HashSet<Integer> history = new HashSet<>();
        while (number != 1) {
            history.add(number);
            int sum = 0;
            while (number != 0) {
                sum += Math.pow(number % 10, 2);
                number /= 10;
            }
            number = sum;
            if (history.contains(number)) {
                return false;
            }
        }
        return true;
    }

    private static void printList(LinkedList<Integer> numbers, int start) {
        System.out.print("The five happy numbers starting from " + start + ": ");
        for (int number : numbers) {
            System.out.print(number + ", ");
        }
    }
}
