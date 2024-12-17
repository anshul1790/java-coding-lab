package com.learn.challenges.leet;

import java.util.*;

public class SumOf4Solution {
    public static void main(String[] args) {
        sumOfFour(new int[]{2, 2, 2, 2, 2, 2}, 8);
    }

    static void sumOfFour (int[]nums, int target) {
        List<List<Integer>> finalList = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int quadSum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (quadSum == target) {
                        finalList.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        break;
                    } else if (quadSum < target) {
                        left ++;
                    } else {
                        right --;
                    }
                }
            }
        }
        Set<List<Integer>> uniqueQuads = new HashSet<>(finalList);
        System.out.println(Arrays.toString(uniqueQuads.toArray()));
    }

}

/*
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

1. Sort the list to leverage the pointers
    -> [-2, -1, 0, 0, 1, 2]
2. Start from index i = 0 to i = len - 1 using initial for loop
    with values ith = -1 and i + 1 = -1
    and left = i + 2, right = len - 1
3. first iteration
    1. [-2, -1, 0, 2] = -1 < 0 so move left pointer
    2. [-2, -1, 0, 2] = -1 < 0 so move left pointer
    3. [-2, -1, 1, 2 = 0, add this to the list and loop ends
4. second iteration
    1.

 */
