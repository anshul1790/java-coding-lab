package com.learn.challenges.revise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Revise25thJan {
    public static void main(String[] args) {
        //twoSumSortedArray(new int[]{3, 2, 4}, 6);
        //removeDuplicateArrays(new int[]{0,0,1,1,1,2,2,3,3,4});
        //moveZeros(new int[]{0, 1, 0, 3, 12});
    }


    static void moveZeros(int[] arr) {
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            if (arr[right] != 0) {
                arr[left] = arr[right];
                left = left + 1;
                right = right + 1;
            } else {
                right ++;
            }
        }
        while (left < arr.length) {
            arr[left++] = 0;
        }
        System.out.println(Arrays.toString(arr));
    }


    // 1, 1, 1, 2, 2, 3
    /*Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]*/
    static void removeDuplicateArrays(int[] arr) {
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            if (arr[left] != arr[right]) {
                arr[++left] = arr[right];
                right = right + 1;
            } else {
                right++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /*
    [3, 2, 4] = 6
     */
    static void twoSumSortedArray(int[] arr, int sum) {
        // Sum and Pos array
        Map<Integer, Integer> targetSumMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!targetSumMap.containsKey(arr[i])) {
                targetSumMap.put(sum-arr[i], i);
                continue;
            }
            System.out.println(targetSumMap.get(arr[i]) + " | " + i);
            break;
        }
    }
}
