package com.learn.leetcodeproblems;

public class ReverseInteger17Nov {
    public static void main(String[] args) {
        System.out.println(reverse(123));
    }

    public static int reverse(int x) {
        int reversed = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            // Check for overflow before actually reversing
             if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && pop > 7)) {
                 return 0; // Overflow condition
             }
             if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && pop < -8)) {
                 return 0; // Underflow condition
             }
            reversed = reversed * 10 + pop;
        }
        return reversed;
    }

    /*public static int reverse1(int x) {
        if (x == 0)
            return x;
        StringBuilder s = new StringBuilder();
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = -(x);
        }
        while (x > 0) {
            int reverseMod = x % 10;
            x = x / 10;
            s.append(reverseMod);
        }
        try {
            if (isNegative) {
                return Integer.parseInt("-" + s);
            } else {
                return Integer.parseInt(s.toString());
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }*/
}
