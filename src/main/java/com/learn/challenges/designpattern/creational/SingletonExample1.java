package com.learn.challenges.designpattern.creational;

public class SingletonExample1 {

    static class SingletonDatabase {

        private static SingletonDatabase instance;

        private SingletonDatabase() {
        }

        public static SingletonDatabase createInstance() {
            if (instance == null) {
                instance = new SingletonDatabase();
                return instance;
            }
            return instance;
        }
    }

}
