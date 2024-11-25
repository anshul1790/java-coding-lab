package com.learn.leetcodeproblems;

public class Palindrome25Nov {
    public static void main(String[] args) {
        System.out.println("Palindrome = " + isPalindrome(-121));
    }

    static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int oldX = x;
        int newX = 0;
        while (x > 0) {
            int popRight = x % 10;
            x = x/ 10;
            newX = newX * 10 + popRight;
        }
        return oldX == newX;
    }
}
