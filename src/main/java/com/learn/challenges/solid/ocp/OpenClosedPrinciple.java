package com.learn.challenges.solid.ocp;

/*
The below class states that using shape object to find
the area of any shape
we are not modifying any existing class however we are open for extension
to any shape, like square, pentagon, polygon etc
 */
public class OpenClosedPrinciple {

    static interface Shape {
        double calculateArea();
    }

    static class Rectangle implements Shape {

        private int length;
        private int breadth;

        public Rectangle(int length, int breadth) {
            this.length = length;
            this.breadth = breadth;
        }

        @Override
        public double calculateArea() {
            return length * breadth;
        }
    }

    static class Circle implements Shape {

        private int radius;

        public Circle(int radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }


}
