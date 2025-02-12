package com.learn.challenges.designpattern.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositePatternExample2 {

    static interface FileSystemComponent {
        void display();
    }

    static class File implements FileSystemComponent {

        String name;

        public File(String name) {
            this.name = name;
        }

        @Override
        public void display() {
            System.out.println("Name of file is " + name);
        }
    }

    static class Directory implements FileSystemComponent {

        String name;
        List<FileSystemComponent> contents = new ArrayList<>();

        public Directory(String name) {
            this.name = name;
        }

        public void addContent(FileSystemComponent component) {
            contents.add(component);
        }

        @Override
        public void display() {
            System.out.println("Directory is " + this.name);
            System.out.println("Directory contents are " + contents.size());
            for (FileSystemComponent component : contents) {
                component.display();
            }
        }
    }


    public static void main(String[] args) {
        FileSystemComponent dir1 = new Directory("dir1");
        FileSystemComponent dir2 = new Directory("dir2");

        FileSystemComponent file1 = new File("file1");
        FileSystemComponent file2 = new File("file2");
        FileSystemComponent file3 = new File("file3");

        ((Directory) dir1).addContent(file1);
        ((Directory) dir1).addContent(file2);
        ((Directory) dir2).addContent(file3);

        ((Directory) dir1).addContent(dir2);

        dir1.display();

    }

}
