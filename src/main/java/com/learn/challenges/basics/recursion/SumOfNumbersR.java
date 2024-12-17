package com.learn.challenges.basics.recursion;

public class SumOfNumbersR {

    public static void main(String[] args) {
        System.out.println(sumNumbers(5));
    }

    static int sumNumbers(int n) {
        if (n <= 1)
            return n;
        return n + sumNumbers(n - 1);
    }
}

/*
stack building
sn(5) -> 5 + sn(4)
sn(4) -> 4 + sn(3)
sn(3) -> 3 + sn(2)
sn(2) -> 2 + sn(1)
sn(1) -> 1

Stack unwind
sn (1) -> 1
sn (2) -> 2 + 1
sn(3) -> 3 + 2 + 1
sn(4) -> 4 + 3 + 2 + 1
sn(5) -> 5 + 4 + 3 + 2 + 1

 */
