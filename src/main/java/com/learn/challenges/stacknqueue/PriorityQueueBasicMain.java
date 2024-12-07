package com.learn.challenges.stacknqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueBasicMain {
    public static void main(String[] args) {
        priorityQueueExample();
    }

    static void priorityQueueExample() {
        Queue<String> priorityQueue = new PriorityQueue<>(String::compareTo);
        priorityQueue.add("Sahil");
        priorityQueue.add("Aanshul");
        priorityQueue.add("Anshul");

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

    }
}
