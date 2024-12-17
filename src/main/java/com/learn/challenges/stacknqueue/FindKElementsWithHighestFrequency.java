package com.learn.challenges.stacknqueue;

import java.util.*;

public class FindKElementsWithHighestFrequency {
    public static void main(String[] args) {
        topKFrequent(new int[]{3, 1, 4, 4, 5, 2, 6, 1}, 2);
    }

    static void topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        System.out.println(minHeap);

    }
}
