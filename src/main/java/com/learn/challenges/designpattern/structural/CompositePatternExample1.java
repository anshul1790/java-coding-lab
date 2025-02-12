package com.learn.challenges.designpattern.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositePatternExample1 {
    static class File {
        private String name;

        public File(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void display() {
            System.out.println("File: " + name);
        }
    }

    static class Directory {
        private String name;
        private List<Object> contents = new ArrayList<>();

        public Directory(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void add(Object obj) {
            contents.add(obj);
        }

        // Here to display list of files and content inside it
        // the logic has become complex, rather we can use the composite
        // pattern
        // Why composite pattern here because we are dealing with nested/tree like structure of
        // objects

        public void display() {
            System.out.println("Directory: " + name);
            for (Object obj : contents) {
                if (obj instanceof File) {
                    ((File) obj).display();
                } else if (obj instanceof Directory) {
                    ((Directory) obj).display();
                }
            }
        }
    }
}
