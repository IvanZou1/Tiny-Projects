package classicalgorithms;

import java.util.Scanner;

/*
 *
 * InsertionSort - An iterative algorithm that sorts an integer array
 * in increasing order in O(n^2) time
 *
 */

public class InsertionSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sort an integer array using Insertion Sort!");
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
        insertionSort(arr);
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

    // O(n^2)
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
