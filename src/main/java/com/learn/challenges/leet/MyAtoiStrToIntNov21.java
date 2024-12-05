package com.learn.challenges.leet;

public class MyAtoiStrToIntNov21 {
    public static void main(String[] args) {
        myAtoi("-123");
    }

    static int myAtoi(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;
        int result = 0;
        int itr = 0;
        int sign = 1;
        if (s.charAt(itr) == '-') {
            itr = itr + 1;
            sign = -1;
        } else if (s.charAt(itr) == '+') {
            itr = itr + 1;
        }
        while (itr < s.length()) {
            char c = s.charAt(itr);
            if (c < '0' || c > '9')
                break;
            if (result > (Integer.MAX_VALUE - (c - '0')) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (int) c - '0';
            itr ++;
        }
        System.out.println(result * sign);
        return result * sign;
    }
}
