package com.learn.challenges.leet;

import java.util.*;

public class TaskScheduler17New {

    static class Task {
        Character id;
        int count;
        int coolDown;

        public Task(Character id, int count, int coolDown) {
            this.id = id;
            this.count = count;
            this.coolDown = coolDown;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    '}';
        }
    }

    public int leastInterval(char[] tasks, int n) {
        //1. create the map of the tasks to be scheduled
        Map<Character, Integer> taskCountMap = new HashMap<>();
        for (char task : tasks) {
            taskCountMap.put(task, taskCountMap.getOrDefault(task, 0) + 1);
        }

        //2. create a priority queue
        /*Queue<Integer> taskHeap = new PriorityQueue<>(Collections.reverseOrder());
        taskHeap.addAll(taskCountMap.values());*/

        Queue<Task> taskHeap = new PriorityQueue<>(
                (Task o1, Task o2) -> Integer.compare(o2.count, o1.count)
        );

        for (Map.Entry<Character, Integer> entry : taskCountMap.entrySet()) {
            taskHeap.add(new Task(entry.getKey(), entry.getValue(), 0));
        }

        //3. schedule the tasks using taskHeap and cooldown queue
        // Queue<int[]> coolDownQueue = new LinkedList<>();

        Queue<Task> coolDownQueue = new LinkedList<>();

        List<Task> taskProcessMap = new ArrayList<>();

        int time = 0;

        while (!taskHeap.isEmpty() || !coolDownQueue.isEmpty()) {
            time ++;

            if (!taskHeap.isEmpty()) {
                Task currentTask = taskHeap.poll();
                taskProcessMap.add(currentTask);
                currentTask.count --;
                if (currentTask.count > 0) {
                    currentTask.coolDown = time + n;
                    coolDownQueue.add(currentTask);
                }
            } else {
                taskProcessMap.add(null);
            }

            if (!coolDownQueue.isEmpty()) {
                if (coolDownQueue.peek().coolDown == time) {
                    Task addTaskToProcessAgain = coolDownQueue.poll();
                    taskHeap.add(addTaskToProcessAgain);
                }
            }
        }
        System.out.println(taskProcessMap);
        return time;
    }

    public static void main(String[] args) {
        TaskScheduler17New scheduler = new TaskScheduler17New();

        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n1 = 2;

        System.out.println("Output: " + scheduler.leastInterval(tasks1, n1)); // Output: 8


        char[] tasks2 = {'A', 'C', 'A', 'B', 'D', 'B'};
        int n2 = 1;
        System.out.println("Output: " + scheduler.leastInterval(tasks2, n2)); // Output: 6

        char[] tasks3 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n3 = 3;
        System.out.println("Output: " + scheduler.leastInterval(tasks3, n3)); // Output: 10

    }
}
