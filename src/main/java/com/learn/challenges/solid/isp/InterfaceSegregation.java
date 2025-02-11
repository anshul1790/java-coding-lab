package com.learn.challenges.solid.isp;

public class InterfaceSegregation {
    static interface Workable {
        void work();
    }

    static interface Eatable {
        void eat();
    }

    static class Developer implements Workable, Eatable {
        @Override
        public void work() {
            System.out.println("Developer is coding.");
        }

        @Override
        public void eat() {
            System.out.println("Developer is eating.");
        }
    }

    static class Robot implements Workable {
        @Override
        public void work() {
            System.out.println("Robot is working.");
        }
    }
}
