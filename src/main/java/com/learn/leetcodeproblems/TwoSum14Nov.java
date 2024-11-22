package com.learn.leetcodeproblems;

import java.util.HashMap;
import java.util.Map;

public class TwoSum14Nov {

    public static void main(String[] args) {
        twoSum(new int[]{3, 6}, 9);
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i < nums.length; i ++) {
            if (numMap.containsKey(nums[i])) {
                return new int[]{numMap.get(nums[i]), i};
            }
            int setItem = target - nums[i];
            numMap.put(setItem, i);
        }
        return null;
    }
}
