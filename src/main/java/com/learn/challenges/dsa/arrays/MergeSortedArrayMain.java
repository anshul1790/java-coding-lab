package com.learn.challenges.dsa.arrays;

import java.util.Arrays;

public class MergeSortedArrayMain {
    public static void main(String[] args) {
        mergeSortedArray(new int[]{1, 7, 10, 11}, new int[]{1, 3, 6, 9, 45, 50});
    }

    // [1, 5, 8, 10] and [2, 3, 6, 9, 12, 15]
    // [1, 2, 3, 5, 6, 8, 10, 12, 15]
    // [1, 2, 3, 4] [5, 6, 7, 8, 9, 10]
    static void mergeSortedArray(int[] array1, int[] array2) {
        int leftListPointer = 0; int rightListPointer = 0; int index = 0;
        int[] newArr = new int[array1.length + array2.length];
        while (leftListPointer < array1.length && rightListPointer < array2.length) {
            if (array1[leftListPointer] < array2[rightListPointer]) {
                newArr[index++] = array1[leftListPointer++];
            } else {
                newArr[index++] = array2[rightListPointer++];
            }
        }
        while (leftListPointer < array1.length) { newArr[index++] = array1[leftListPointer++]; }
        while (rightListPointer < array2.length) { newArr[index++] = array2[rightListPointer++]; }
        System.out.println(Arrays.toString(newArr)); }
}