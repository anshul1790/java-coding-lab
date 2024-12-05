package com.learn.challenges.arrays;

import java.util.Arrays;

public class LeftShiftArrayByPos1 {
    public static void main(String[] args) {
        //leftShiftArray(new int[]{1, 2, 3, 4, 5}, 3);
        rightShiftArray1(new int[]{1, 2, 3, 4, 5}, 2);
    }

    // [1, 2, 3, 4, 5]
    // 1 pos -> [2, 3, 4, 5, 1]
    // 2 pos -> [3, 4, 5, 1, 2]
    static void leftShiftArray(int[] array, int pos) {
        int indexPos = 0;
        while (indexPos < pos) {
            int temp = array[0];
            for (int i = 0; i < array.length - 1; i ++) {
                array[i] = array [i + 1];
            }
            array[array.length - 1] = temp;
            indexPos ++;
        }
        System.out.println(Arrays.toString(array));
    }

    // [1, 2, 3, 4, 5]
    // [5, 1, 2, 3, 4]
    // 2 pos -> [5, 4, 1, 2, 3]
    static void rightShiftArray1(int[] array, int pos) {
        while (pos > 0) {
            int temp = array[array.length - 1];
            for (int i = array.length - 1; i > 0; i --) {
                array[i] = array[i-1];
            }
            array[0] = temp;
            pos --;
        }
        System.out.println(Arrays.toString(array));
    }
}
