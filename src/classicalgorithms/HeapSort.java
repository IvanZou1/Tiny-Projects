package classicalgorithms;

import java.util.Scanner;

/*
 *
 * HeapSort - An iterative algorithm that uses the max-heap date structure to
 * sort an integer array in increasing order in O(nlogn) time
 *
 */

public class HeapSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sort an integer array using Heap Sort!");
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
        heapSort(arr);
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


    // O(nlogn)
    private static void heapSort(int[] arr) {
        int heapSize = arr.length;
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapSize--;
            maxHeapify(arr, 0, heapSize);
        }
    }

    // O(n)
    private static void buildMaxHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            maxHeapify(arr, i, arr.length);
        }
    }

    // O(logn)
    private static void maxHeapify(int[] arr, int i, int heapSize) {
        int leftIndex = (2 * i) + 1;
        int rightIndex = (2 * i) + 2;
        int largest = i;
        if (leftIndex < heapSize && arr[leftIndex] > arr[i]) {
            largest = leftIndex;
        }
        if (rightIndex < heapSize && arr[rightIndex] > arr[largest]) {
            largest = rightIndex;
        }
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, heapSize);
        }
    }

    // O(1)
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
