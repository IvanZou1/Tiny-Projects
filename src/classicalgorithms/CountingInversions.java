package classicalgorithms;

import java.util.Arrays;
import java.util.Scanner;

/*
 *
 * CountingInversions - A divide and conquer algorithm that counts the number
 * of inversions in an integer array as well as sort the array in increasing
 * order in O(nlogn) time
 *
 * Inversion - Given a sequence of numbers a1, a2, ..., an, for indices i < j,
 * if ai > aj, in other words, if ai and aj are out of order, then there is an
 * inversion
 *
 * Ex -
 * [2, 4, 1, 3, 5] have 3 inversions because of the numbers (2, 1), (4, 1), and
 * (4, 3)
 *
 */

public class CountingInversions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Count the inversions in an integer array!");
        System.out.print("Input the number of integers in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Input the " + n + " integers for the array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + ") ");
            arr[i] = sc.nextInt();
        }
        sc.close();
        System.out.println("Your Original Array: " + arrToString(arr));
        int inversions = countInversionsAndSort(arr, 0, n - 1);
        System.out.println("The Number of Inversions: " + inversions);
        System.out.println("The Sorted Array: " + arrToString(arr));
    }

    private static String arrToString(int[] arr) {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                str.append(arr[i]);
            } else {
                str.append(arr[i]);
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    private static int countInversionsAndSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return 0;
        } else {
            int midIndex = (leftIndex + rightIndex) / 2;

            int leftCount = countInversionsAndSort(arr, leftIndex, midIndex);
            int rightCount = countInversionsAndSort(arr, midIndex + 1, rightIndex);
            int mergeCount = mergeAndCount(arr, leftIndex, midIndex, rightIndex);

            return leftCount + rightCount + mergeCount;
        }
    }

    private static int mergeAndCount(int[] arr, int leftIndex, int midIndex, int rightIndex) {
        int[] leftArr = Arrays.copyOfRange(arr, leftIndex, midIndex + 1);
        int[] rightArr = Arrays.copyOfRange(arr, midIndex + 1, rightIndex + 1);
        int currLeftIndex = 0, currRightIndex = 0, arrIndex = leftIndex;
        int count = 0;

        while (currLeftIndex < leftArr.length && currRightIndex < rightArr.length) {
            int leftNum = leftArr[currLeftIndex];
            int rightNum = rightArr[currRightIndex];
            if (leftNum <= rightNum) {
                arr[arrIndex] = leftNum;
                arrIndex++;
                currLeftIndex++;
            } else {
                arr[arrIndex] = rightNum;
                arrIndex++;
                currRightIndex++;
                count += (midIndex + 1) - (leftIndex + currLeftIndex);
            }
        }
        while (currLeftIndex < leftArr.length) {
            arr[arrIndex] = leftArr[currLeftIndex];
            arrIndex++;
            currLeftIndex++;
        }
        while (currRightIndex < rightArr.length) {
            arr[arrIndex] = rightArr[currRightIndex];
            arrIndex++;
            currRightIndex++;
        }
        return count;
    }
}
