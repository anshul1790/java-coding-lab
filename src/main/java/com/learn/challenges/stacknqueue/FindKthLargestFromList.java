package com.learn.challenges.stacknqueue;

import java.util.PriorityQueue;
import java.util.Queue;

public class FindKthLargestFromList {

    public static void main(String[] args) {
        largestKthElement(new int[]{1, 2, 3, 4, 5, 6}, 3);
    }


    static void largestKthElement(int[] list, int k) {
        Queue<Integer> minHeapQueue = new PriorityQueue<>(k);
        int index = 0;
        while (index < k) {
            minHeapQueue.add(list[index]);
            index ++;
        }
        for (int i = k; i < list.length; i++) {
            if (list[i] > minHeapQueue.peek()) {
                minHeapQueue.poll();
                minHeapQueue.add(list[i]);
            }
        }
        System.out.println(minHeapQueue.poll());
    }
}
