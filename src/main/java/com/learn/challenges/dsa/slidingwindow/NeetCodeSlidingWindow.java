package com.learn.challenges.dsa.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class NeetCodeSlidingWindow {
    public static void main(String[] args) {
        //maxProfit(new int[]{7,1,5,3,6,4});
        lengthOfLongestSubstring(" ");
    }


    /*
    Input: s = "zxyzfxyz"
    Output: 3

    1. Add -> [z,x,y]
    2. Remove -> [z,x,y]
    3.
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        char[] charArray = s.toCharArray();
        Set<Character> uniques = new HashSet<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            if (!uniques.contains(charArray[right])) {
                uniques.add(charArray[right]);
                maxLen = Math.max(maxLen, uniques.size());
                right ++;
            } else {
                uniques.remove(charArray[left]);
                left++;
            }
        }
        System.out.println(maxLen);
        return maxLen;
    }

    /*
    Input: prices = [10,1,5,6,7,1]
    Output: 6
    */
    public static int maxProfit(int[] prices) {
        int sellIndex = 1;
        int buyIndex = 0;
        int maxProfit = 0;
        while (sellIndex < prices.length) {
            int currentProfit = prices[sellIndex] - prices[buyIndex];
            if (prices[sellIndex] < prices[buyIndex]) {
                buyIndex = sellIndex;
            } else {
                maxProfit = Math.max(maxProfit, currentProfit);
            }
            sellIndex++;
        }
        System.out.println(maxProfit);
        return maxProfit;
    }
}
