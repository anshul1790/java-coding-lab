package com.learn.challenges.leet;

import java.util.*;

public class TaskScheduler17Dec {

    public int leastInterval(char[] tasks, int n) {
        //1. create the map of the tasks to be scheduled
        Map<Character, Integer> taskCountMap = new HashMap<>();
        for (char task : tasks) {
            taskCountMap.put(task, taskCountMap.getOrDefault(task, 0) + 1);
        }
        //2. create a priority queue
        Queue<Integer> taskHeap = new PriorityQueue<>(Collections.reverseOrder());
        taskHeap.addAll(taskCountMap.values());

        //3. schedule the tasks using taskHeap and cooldown queue
        Queue<int[]> coolDownQueue = new LinkedList<>();

        int time = 0;

        while (!taskHeap.isEmpty() || !coolDownQueue.isEmpty()) {
            time ++;

            if (!taskHeap.isEmpty()) {
                int count = taskHeap.poll() - 1;
                if (count > 0) {
                    coolDownQueue.add(new int[]{time + n, count});
                }
            }

            if (!coolDownQueue.isEmpty()) {
                if (time == coolDownQueue.peek()[0]) {
                    taskHeap.add(coolDownQueue.poll()[1]);
                }
            }
        }
        return time;
    }

    public static void main(String[] args) {
        TaskScheduler17Dec scheduler = new TaskScheduler17Dec();

        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n1 = 2;

        System.out.println("Output: " + scheduler.leastInterval(tasks1, n1)); // Output: 8

        /*
        char[] tasks2 = {'A', 'C', 'A', 'B', 'D', 'B'};
        int n2 = 1;
        System.out.println("Output: " + scheduler.leastInterval(tasks2, n2)); // Output: 6

        char[] tasks3 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n3 = 3;
        System.out.println("Output: " + scheduler.leastInterval(tasks3, n3)); // Output: 10
        */
    }
}
