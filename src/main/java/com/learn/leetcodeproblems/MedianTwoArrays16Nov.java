package com.learn.leetcodeproblems;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MedianTwoArrays16Nov {
    public static void main(String[] args) {
        double median = findMedianSortedArrays2(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(median);
        //System.out.println(median);
        // binarySearch(new int[]{10, 20, 35, 50, 65, 70, 75, 85, 90, 99 }, 80);
    }


    static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        int partitionX, partitionY;
        while (left <= right) {
            partitionX = (left + right) / 2;
            partitionY = (m + n + 1) / 2 - partitionX;

            int leftMaxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int rightMinX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int leftMaxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int rightMinY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (leftMaxX <= rightMinY && leftMaxY <= rightMinX) {
                // Correct partition found
                if ((m + n) % 2 == 0) {
                    return (double) (Math.max(leftMaxX, leftMaxY) + Math.min(rightMinX, rightMinY)) / 2;
                } else {
                    return (double) Math.max(leftMaxX, leftMaxY);
                }
            } else if (leftMaxX > rightMinY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted");
    }

    static int binarySearch(int[] arr, int num) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == num) {
                System.out.println(arr[mid] + " and index " + mid);
                return mid;
            } else if (num < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println("num not found " + num);
        return -1;
    }


    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] finalArray;
        finalArray = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).sorted().toArray();
        if (finalArray.length % 2 != 0)
            return finalArray[finalArray.length/2];
        return (double) ((finalArray[finalArray.length / 2]) + (finalArray[(finalArray.length - 1) / 2])) / 2;
    }
}
