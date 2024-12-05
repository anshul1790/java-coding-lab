package com.learn.challenges.arrays;

import java.util.Arrays;

public class MoveZerosMain {
    public static void main(String[] args) {
        moveZeroes(new int[]{1, 0, 3, 4});
    }

    // [1,0,3,12,0]
    // [1,0,3,12,0]
    //Output: [1,3,12,0,0]
    static void moveZeroes(int[] arr) {
        int newIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                arr[newIndex++] = arr[i];
            }
        }
        while (newIndex < arr.length) {
            arr[newIndex++] = 0;
        }
        System.out.println(Arrays.toString(arr));
    }
}
