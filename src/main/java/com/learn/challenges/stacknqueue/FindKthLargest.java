package com.learn.challenges.stacknqueue;
import java.util.PriorityQueue;

public class FindKthLargest {
    public static int findKthLargest(int[] nums, int k) {
        // Initialize a min-heap with capacity k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        // Iterate over the elements in the array
        for (int num : nums) {
            minHeap.add(num);
            // Ensure the heap size does not exceed k
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The root of the heap is the k-th largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        System.out.println("The 3rd largest element is: " + findKthLargest(nums, k));
    }
}
