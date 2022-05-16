import java.util.Scanner;

/*
 *
 * MergeSort - A divide and conquer algorithm that sorts an integer array
 * in increasing order in O(nlogn) for n = array size
 *
 */

public class MergeSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sorting an integer array using Merge Sort!");
        System.out.println("Input the number of integers in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Input the " + n + " integers for the array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + ") ");
            arr[i] = sc.nextInt();
        }
        System.out.println("Your Original Array: " + arrToString(arr));
        mergeSort(arr, 0, n - 1);
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

    // O(nlogn) for n = arr.length
    private static void mergeSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int midIndex = (leftIndex + (rightIndex - 1)) / 2;
            mergeSort(arr, leftIndex, midIndex);
            mergeSort(arr, midIndex + 1, rightIndex);
            merge(arr, leftIndex, midIndex, rightIndex);
        }
    }

    // O(n)
    private static void merge(int[] arr, int leftIndex, int midIndex, int rightIndex) {
        int leftLength = midIndex - leftIndex + 1;
        int rightLength = rightIndex - midIndex;
        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];
        for (int i = 0; i < leftLength; i++) {
            leftArr[i] = arr[leftIndex + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightArr[i] = arr[midIndex + 1 + i];
        }
        int l = 0;
        int r = 0;
        int currArrIndex = leftIndex;

        while (l < leftLength && r < rightLength) {
            if (leftArr[l] <= rightArr[r]) {
                arr[currArrIndex] = leftArr[l];
                l++;
            } else {
                arr[currArrIndex] = rightArr[r];
                r++;
            }
            currArrIndex++;
        }
        while (l < leftLength) {
            arr[currArrIndex] = leftArr[l];
            l++;
            currArrIndex++;
        }
        while (r < rightLength) {
            arr[currArrIndex] = rightArr[r];
            r++;
            currArrIndex++;
        }
    }
}
