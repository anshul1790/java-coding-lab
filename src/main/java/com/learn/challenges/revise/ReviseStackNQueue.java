package com.learn.challenges.revise;

import java.util.*;
import java.util.stream.Collectors;

public class ReviseStackNQueue {

    static class Student {
        String name;
        Integer rollNo;
        Integer cgpa;

        public Student(String name, Integer rollNo, Integer cgpa) {
            this.name = name;
            this.rollNo = rollNo;
            this.cgpa = cgpa;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", rollNo='" + rollNo + '\'' +
                    ", cgpa='" + cgpa + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        //reverseIndividualWord("Geeks for Geeks");
        //findMaxUniqueSubset(new int[]{1, 2, 1, 1, 2, 1, 3, 4, 9, 5}, 5);
        //compareStudentsUsingComparator();
        //largestKthElement(new int[]{6, 2, 3, 5, 4, 7, 1, 9, 0, 4, 5}, 2);
        findKElementsWithHighFrequency(new int[]{1, 2, 2, 3, 4, 4}, 3);
    }

    //3, 1, 4, 4, 5, 2, 6, 1
    // output = 4, 1
    static void findKElementsWithHighFrequency(int[] arr, int k) {
        Map<Integer, Integer> elCount = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            elCount.put(arr[i], elCount.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(elCount);
        Queue<Map.Entry<Integer, Integer>> freqCount = new PriorityQueue<>((o1, o2) ->
                o1.getValue().compareTo(o2.getValue())
        );
        for (Map.Entry<Integer, Integer> entry : elCount.entrySet()) {
            freqCount.add(entry);
            if (freqCount.size() > k) {
                freqCount.poll();
            }
        }
        System.out.println(freqCount);
    }

    static void largestKthElement(int[] arr, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>(k);
        int index = 0;
        while (k > index) {
            minHeap.add(arr[index]);
            index++;
        }
        for (int i = k; i < arr.length; i++) {
            if (!minHeap.isEmpty() && arr[i] > minHeap.peek()) {
                int elr = minHeap.poll();
                System.out.println("element removed = " + elr);
                minHeap.add(arr[i]);
            }
        }
        System.out.println(minHeap.peek());
    }

    static void compareStudentsUsingComparator() {
        Student s1 = new Student("anshul", 1, 9);
        Student s2 = new Student("sahil", 2, 8);
        Student s3 = new Student("radhika", 3, 5);
        Student s4 = new Student("radhika", 4, 5);
        List<Student> students = new ArrayList<>(List.of(s1, s2, s3, s4));
        students.sort(new Comparator<>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (!Objects.equals(o1.cgpa, o2.cgpa)) {
                    return Integer.compare(o2.cgpa, o1.cgpa);
                } else if (!Objects.equals(o1.name, o2.name)) {
                    return o1.name.compareTo(o2.name);
                } else {
                    return Integer.compare(o2.rollNo, o1.rollNo);
                }
            }
        });
        System.out.println(students);
    }

    /*
    Input:
        8 4 (window size)
        5 3 5 2 3 2 4 5
    Output:
        3
    Explanation
        For subarrays of size 4:
        [5, 3, 5, 2] has 3 unique integers (5, 3, 2)
        [3, 5, 2, 3] has 3 unique integers (3, 5, 2)
        [5, 2, 3, 2] has 3 unique integers (5, 2, 3)
        [2, 3, 2, 4] has 3 unique integers (2, 3, 4)
        [3, 2, 4, 5] has 4 unique integers (3, 2, 4, 5)
   */

    // using queue to solve this
    static void findMaxUniqueSubset(int[] arr, int k) {
        int max = 0;
        Queue<Integer> integersSubset = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            integersSubset.add(arr[i]);
            if (integersSubset.size() == k) {
                List<Integer> uniques = integersSubset.stream().distinct().toList();
                max = Math.max(max, uniques.size());
                integersSubset.remove();
            }
        }
        System.out.println(max);
    }

    /*
    Input :  Geeks for Geeks
    Output : skeeG rof skeeG
     */
    static void reverseIndividualWord(String str) {
        Stack<Character> charStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                charStack.add(c);
            } else {
                while (!charStack.empty()) {
                    System.out.print(charStack.pop());
                }
                System.out.print(" ");
            }
        }
        if (!charStack.empty()) {
            while (!charStack.isEmpty()) {
                System.out.print(charStack.pop());
            }
        }
    }

}

