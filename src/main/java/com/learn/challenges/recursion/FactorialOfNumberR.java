package com.learn.challenges.recursion;

public class FactorialOfNumberR {

    public static void main(String[] args) {
        System.out.println(factorialOfNum(5));
    }

    static int factorialOfNum(int n) {
        if (n <= 1)
            return n;
        return n * factorialOfNum(n-1);
    }
}

