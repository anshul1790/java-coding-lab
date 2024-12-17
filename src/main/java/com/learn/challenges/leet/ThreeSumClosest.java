package com.learn.challenges.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumClosest {
    public static void main(String[] args) {
        threeSumClosest(new int[]{2, 5, 6, 7}, 16);
    }

    /*
    1. Sort the array to find all triplets in a list
    2. Find the triplet sum first
        - In this approach, create initial pointer as ith value
        - define left as ith + 1 and right as lenth of array
        - move you right towards length while it reaches till left
        - find the sum of that triplet
    3. find the distance between target and calculated value.

    nums = [1, 2, 3, 4, 5], target = 10
    targetSum = 7
    target = 3
    /*
    1 + 2 + 5 = 8
    1 + 2 + 4 = 7
    1 + 2 + 3 = 6
    -----------------
    2 + 3 + 5 = 10
    2 + 3 + 4 = 9
    ------------------
    3 + 4 + 5 = 12
   */

    /*
    [2, 5, 6, 7]

    2, 5, 6 = 13
    2, 5, 7 = 14
    2, 6, 7 = 15
    5, 6, 7 = 18

    */

    static void threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int tripletSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(tripletSum - target) < Math.abs(closestSum - target)) {
                    closestSum = tripletSum;
                }
                if (tripletSum < target) {
                    left++;
                } else if (tripletSum > target) {
                    right--;
                }
            }
        }
        System.out.println(closestSum);
    }
}