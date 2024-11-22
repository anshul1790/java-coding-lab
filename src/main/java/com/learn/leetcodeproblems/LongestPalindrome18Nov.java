package com.learn.leetcodeproblems;

import java.util.HashSet;
import java.util.Set;

public class LongestPalindrome18Nov {

    public static void main(String[] args) {
        //longestPalindromeSubstring("bb");
        listAllPalindromesInString("babab");
    }


    static void listAllPalindromesInString(String s) {
        int start = 0, end = 0;
        Set<String> uniquePalindromes = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            int oddLen = expandFromI(s, i, i);
            int evenLen = expandFromI(s, i, i + 1);
            int len = Math.max(oddLen, evenLen);
            if (len >= (end - start)) {
                start = i - (len-1)/2;
                end = i + len/2;
                uniquePalindromes.add(s.substring(start, end + 1));
            }
        }
        System.out.println(uniquePalindromes);
    }

    static void longestPalindromeSubstring(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddLen = expandFromI(s, i, i);
            int evenLen = expandFromI(s, i, i + 1);
            int len = Math.max(oddLen, evenLen);
            if (len > (end - start)) {
                start =  i - (len - 1)/2;
                end = i + len/2;
            }
        }
        System.out.println("Longest palindrome is " + s.substring(start, end + 1));
    }

    static int expandFromI(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;right++;
        }
        return right - left - 1;
    }


    static void isPalindrome(String s) {
        int left = 1, right = s.toCharArray().length - 1;
        boolean isPalindrome = false;
        while (left <= right) {
            if (s.charAt(left-1) != s.charAt(right)) {
                isPalindrome = false;
                break;
            } else {
                isPalindrome = true;
            }
            left ++;
            right --;
        }
        System.out.println("Palindrome = " + isPalindrome);
    }
}
