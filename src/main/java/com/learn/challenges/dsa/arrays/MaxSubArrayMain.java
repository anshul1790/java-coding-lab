package com.learn.challenges.dsa.arrays;

public class MaxSubArrayMain {
    public static void main(String[] args) {
        maxSubArray(new int[] {2, 3, -8, 7, -1, 2, 3});
    }
    // input -> [-2,1,-3,4,-1,2,1,-5,4]
    // output -> 6, [4,-1,2,1]
    static void maxSubArray(int[] array) {
        int resultMax = array[0];
        int currentMax = array[0];
        for (int i = 1; i < array.length; i++) {
            currentMax = Math.max(array[i], currentMax + array[i]);

            resultMax = Math.max(resultMax, currentMax);
        }
        System.out.println(resultMax);
    }
}
