package classicalgorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/*
 *
 * QuickSort - A divide and conquer algorithm that sorts an integer array
 * in increasing order in expected O(nlogn) time and worst-case O(n^2) time
 * for n = array size
 *
 */

public class QuickSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sort an integer array using Quick Sort!");
        System.out.println("Input the number of integers in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Input the " + n + " integers for the array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + ") ");
            arr[i] = sc.nextInt();
        }
        System.out.println("Your Original Array: " + arrToString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("The Sorted Array: " + arrToString(arr));
        sc.close();
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

    private static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (rightIndex > leftIndex) {
            int pIndex = getRandomPivotIndex(leftIndex, rightIndex);
            int location = partition(arr, leftIndex, rightIndex, pIndex);
            quickSort(arr, leftIndex, location - 1);
            quickSort(arr, location + 1, rightIndex);
        }
    }

    private static int getRandomPivotIndex(int min, int max) {
        return BigDecimal.valueOf((int) ((Math.random() * (max - min)) + min))
                .setScale(0, RoundingMode.HALF_UP).intValue();
    }

    private static int partition(int[] arr, int leftIndex, int rightIndex, int pIndex) {
        int pivot = arr[pIndex];
        swap(arr, pIndex, rightIndex);
        int left = leftIndex;
        int right = rightIndex - 1;
        while (left <= right) {
            if (arr[left] <= pivot) {
                left++;
            } else {
                swap(arr, left, right);
                right--;
            }
        }
        swap(arr, left, rightIndex);
        return left;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
