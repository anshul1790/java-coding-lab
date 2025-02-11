package com.learn.challenges.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoPointerProblems {
    public static void main(String[] args) {
        //twoSumSortedArray(new int[]{3, 2, 4}, 6);
        //twoSumWithoutSortedArray(new int[]{2, 7, 9, 11}, 9);
        //removeDuplicatesArray(new int[]{0,0,1,1,1,2,2,3,3,4});
        moveZeroes(new int[]{0,1,0,3,12});

    }

    /*
        Input: nums = [3, 2, 4], target = 6
        Output: [0,1]
    */
    static void twoSumSortedArray(int[] n, int target) {
        Arrays.sort(n);
        int left = 0; int right = n.length-1;
        while (left < right) {
            int x = n[left];
            int y = n[right];
            int xy = x + y;
            if (xy == target) {
                System.out.printf("index are %d and %d", left, right);
                break;
            } else if (xy < target) {
                left ++;
            } else {
                right --;
            }
        }
    }

    static void twoSumWithoutSortedArray(int[] n, int target) {
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < n.length; i++) {
            if (!targetMap.containsKey(n[i])) {
                targetMap.put(target - n[i], i);
            } else {
                System.out.printf("answer is %d and %d ", targetMap.get(n[i]), i);
                break;
            }
        }
    }

    /*
    Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]

    Use left and right pointers
    > if left doesnt match with right, then move left pos by one
    > if it matches with it's last left element then only move right
     */
    static void removeDuplicatesArray(int[] n) {
        int left = 0;
        int right = 0;
        while (right < n.length) {
            if (n[left] != n[right]) {
                n[++left] = n[right];
                right = right + 1;
            } else {
                right++;
            }
        }
        System.out.println(Arrays.toString(n));
        while (left < n.length-1) {
            n[++left] = 0;
        }
        System.out.println(Arrays.toString(n));
    }

    /*
    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]
     */
    static void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left++] = nums[right];
                right = right + 1;
            } else {
                right ++;
            }
        }
        while (left < nums.length) {
            nums[left] = 0;
            left ++;
        }
        System.out.println(Arrays.toString(nums));
    }

    /*
    > [1, 2, 0, 0]

     */
    static void sortColors(int[] nums) {

    }
}
