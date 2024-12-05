package com.learn.challenges.leet;

import java.util.HashSet;
import java.util.Set;

/*
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
 */
public class LengthLongestSubStrNov15 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    static int lengthOfLongestSubstring(String s) {
        Set<Character> charSet = new HashSet<>();
        char[] charArray = s.toCharArray();
        int index = 0, window = 0;
        int max = 0;
        while (index < s.length()) {
            char currentChar = charArray[index];
            if (!charSet.contains(currentChar)) {
                charSet.add(currentChar);
                max = Math.max(max, charSet.size());
                index++;
            } else {
                charSet.remove(charArray[window]);
                window++;
            }
        }
        return max;
    }
}
