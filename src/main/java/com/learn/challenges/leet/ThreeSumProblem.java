package com.learn.challenges.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumProblem {

    public static void main(String[] args) {
        threeSum(new int[]{0,0,0});
    }

    /*
    [-1,0,1,2,-1,-4]
     1. sort the array -
        [-4, -1, -1, 0, 1, 2]
     2. Take i from n elements to start with
        - have two pointers, left = i+1 and right = max
        - start adding ith with left and right
        - if addition is less than target then increase left else decrease right
      3. Continue this while i reaches till n

     */
    static void threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> finalList = new ArrayList<>();
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int theSum =  nums[i] + nums[left] + nums[right];
                if (theSum < target) {
                    left ++;
                } else if (theSum > target) {
                    right --;
                } else {
                    finalList.add(List.of(nums[i], nums[left], nums[right]));
                    break;
                }
            }
        }
        System.out.println(finalList);
    }

}
