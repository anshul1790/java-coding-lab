package com.learn.challenges.hackerrank;

import java.util.*;

class Student {

    int id;
    String name;
    double cgpa;

    Student (int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }
}

class Priorities {
    List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>(
                (Student o1, Student o2) -> {
                    if (o1.getCgpa() != o2.getCgpa()) {
                        return Double.compare(o2.getCgpa(), o1.getCgpa());
                    } else if (!Objects.equals(o1.getName(), o2.getName())) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });
        for (String event : events) {
            if (event.contains("ENTER")) {
                String[] split = event.split( " ");
                Student student = new Student(
                        Integer.parseInt(split[3]), split[1], Double.parseDouble(split[2]));
                studentPriorityQueue.add(student);
            } else if (event.contains("SERVED")) {
                studentPriorityQueue.poll();
            }
        }
        List<Student> finalList = new ArrayList<>();
        while (!studentPriorityQueue.isEmpty()) {
            finalList.add(studentPriorityQueue.poll());
        }
        System.out.println(finalList);
        return finalList;
    }
}


public class StudentRankingsPriorityQueue {

    private static final Priorities priorities = new Priorities();

    public static void main(String[] args) {
        List<String> events = new ArrayList<>();
        events.add("ENTER John 3.75 50");
        events.add("ENTER Mark 3.8 24");
        events.add("ENTER Shafaet 3.7 35");
        events.add("SERVED");
        events.add("SERVED");
        events.add("ENTER Samiha 3.85 36");
        events.add("SERVED");
        events.add("ENTER Ashley 3.9 42");
        events.add("ENTER Maria 3.6 46");
        events.add("ENTER Anik 3.95 49");
        events.add("ENTER Dan 3.95 50");
        events.add("SERVED");

        priorities.getStudents(events);
    }
}
