package com.learn.challenges.hackerrank;

import java.util.*;

public class FindMaxUniqueSubsetQueue {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> mapCount = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.add(num);
            mapCount.put(num, mapCount.getOrDefault(num, 0) + 1);
            if (deque.size() == m) {
                max = Math.max(max, mapCount.size());
                int removeF = deque.removeFirst();
                int count = mapCount.get(removeF);
                if (count == 0) {
                    mapCount.remove(removeF);
                } else {
                    mapCount.put(removeF, count - 1);
                }
            }
        }
        System.out.println(max);
    }
}


