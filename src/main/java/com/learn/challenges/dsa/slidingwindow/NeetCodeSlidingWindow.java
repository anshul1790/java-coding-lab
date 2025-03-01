package com.learn.challenges.dsa.slidingwindow;

import java.util.*;

public class NeetCodeSlidingWindow {
    public static void main(String[] args) {
        //maxProfit(new int[]{7,1,5,3,6,4});
        //lengthOfLongestSubstring(" ");
        //charReplacement("AAABABB", 1);
        //longestSubString("zxyzxyz");
        checkInclusion("adc", "dcda");
    }

    public static boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int[] s1HashCount = new int[26];
        int[] s2HashCount = new int[26];

        if (s1Len > s2Len) {
            return false;
        }

        for (int i = 0; i < s1Len; i++) {
            s1HashCount[s1.charAt(i) - 'a']++;
            s2HashCount[s2.charAt(i) - 'a']++;
        }

        for (int i = s1Len; i < s2Len; i++) {
            if (Arrays.equals(s1HashCount, s2HashCount)) {
                return true;
            }
            s2HashCount[s2.charAt(i - s1Len) - 'a']--;
            s2HashCount[s2.charAt(i) - 'a']++;
        }

        // Check the last window
        return Arrays.equals(s1HashCount, s2HashCount);
    }

    /*
    Input: s = "AAABABB", k = 2
    Output: 5

    Formula: (Window size)−(maxFreq) ≤ k
    What does it mean?
    Window size = right - left + 1 (current substring length)
    maxFreq = highest count of any character in the window
    k = allowed replacements
    The formula ensures that the number of characters to change
    (everything except the most frequent character) does not exceed k.
                             window size  frequency
    1. {A:1} ; maxFreq = 1 ; (0 - 0 + 1) - 1; 0 > K ; maxLen = 1 // no need to shrink
    2. {A:2} ; maxFreq = 2 ; (1 - 0 + 1) - 2; 0 > K ; maxLen = 2 // no need to shrink
    3. {A:3} ; maxFreq = 3 ; (2 - 0 + 1) - 3; 0 > K ; maxLen = 3 // no need to shrink
    4. {A:3, B:1} ; maxFreq = 3 ; (3 - 0 + 1) - 3; 1 > K ; maxLen = 4 // no need to shrink
    5. {A:4, B:1} ; maxFreq = 4 ; (4 - 0 + 1) - 4; 1 > K ; maxLen = 5 // no need to shrink
    6. {A:4, B:2} ; maxFreq = 4 ; (5 - 0 + 1) - 4; 2 > K ; maxLen = 5 // shrink the window here, now
        // A becomes 3 and now left becomes left++;;
    7. {A:3, B:3} ; maxFreq = 4 ; (6 - 1 + 1) - 4; 2 > K ; maxLen = 5 // invalid case
        shrink window from left
        {A:2, B:3} and left = 2; right = 6
    8. loop ends here

     */
    public static int characterReplacement(String s, int k) {
        int left = 0, maxFreq = 0, maxLen = 0;
        int[] charFreq = new int[26];
        for (int right = 0; right < s.length(); right ++) {
            char currentChar = s.charAt(right);
            charFreq[currentChar - 'A']++;
            maxFreq = Math.max(maxFreq, charFreq[currentChar - 'A']);
            // If replacements needed exceed k, shrink the window
            if ((right - left + 1) - maxFreq > k) {
                charFreq[s.charAt(left) - 'A']++;
                left ++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }
        System.out.println(Arrays.toString(charFreq));
        return 0;
    }

    /*
    Input: s = "zxyzfxyz"
    Output: 3

    1. Add -> [z,x,y]
    2. Remove -> [z,x,y]
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

    /*
    Input: s = "xxxx"
    Output: 3
     */
    public static void longestSubString(String s) {
        int maxLen = 0;
        int left = 0, right = 0;
        Set<Character> unique = new HashSet<>();
        while (right < s.length()) {
            char current = s.charAt(right);
            if (!unique.contains(current)) {
                unique.add(current);
                right ++;
            } else {
                unique.remove(s.charAt(left));
                left ++;
            }
            maxLen = Math.max(maxLen, right - left);
        }
        System.out.println(maxLen);
    }

    public static void charReplacement(String s, int k) {
        int left = 0, right =0;
        int maxFreq = 0, maxLen = 0;
        int[] charFreq = new int[26];
        while (right < s.length()) {
            char currentChar = s.charAt(right);
            charFreq[currentChar - 'A']++;
            maxFreq = Math.max(maxFreq, charFreq[currentChar - 'A']);
            int currentWindow = right - left + 1;
            if (currentWindow - maxFreq > k) {
                charFreq[s.charAt(left) - 'A']--;
                left ++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right ++;
        }

        System.out.println(maxLen);

    }

}
