package com.learn.challenges.recursion;

public class RecursionDemo {
    public static void main(String[] args) {
        printNumbers(5);
    }

    static void printNumbers(int num) {
        if (num == 0) {
            return;
        }
        printNumbers(num - 1);
        System.out.println(num);
    }
}

