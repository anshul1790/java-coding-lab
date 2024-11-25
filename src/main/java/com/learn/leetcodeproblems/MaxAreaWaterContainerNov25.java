package com.learn.leetcodeproblems;

public class MaxAreaWaterContainerNov25 {
    public static void main(String[] args) {
        maxArea(new int[]{2, 1, 2});
    }

    static int maxArea(int[] height) {
        int n = height.length;
        int containerSize = 0;
        int leftPointer = 0, rightPointer = n - 1;
        while (leftPointer != rightPointer) {
            int containerHeight, containerLength;
            if (height[leftPointer] < height[rightPointer]) {
                containerHeight = height[leftPointer];
                leftPointer ++;
            } else {
                containerHeight = height[rightPointer];
                rightPointer --;
            }
            containerLength = rightPointer - leftPointer + 1;
            if (containerLength * containerHeight > containerSize) {
                containerSize = containerLength * containerHeight;
            }
        }
        return containerSize;
    }
}
