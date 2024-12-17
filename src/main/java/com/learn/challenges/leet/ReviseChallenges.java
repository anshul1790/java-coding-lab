package com.learn.challenges.leet;

import java.util.*;

public class ReviseChallenges {

    static class Node {
        int val;
        Node nextNode;
        public Node(int val) {
            this.val = val;
            this.nextNode = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", nextNode=" + nextNode +
                    '}';
        }
    }

    public static void main(String[] args) {
        // bruteForceSum(new int[]{2, 7, 11, 15}, 9);
        // toSumUsingMap(new int[]{3, 2, 4}, 6);
        /*Node l1 = createLinkedList(new int[]{5, 5, 5});
        Node l2 = createLinkedList(new int[]{5, 5, 5});
        addTwoNumInLinkedList(l1, l2);*/
        /*longestSubString("abcabcbb");*/
        //longestPalindromicString("aba");
        //zigzagConversion("PAYPALISHIRING", 4);
        //reverseInteger(321231239);
        //palindromeNum(121);
        // maxAreaInWaterContainer(new int[]{1,3, 4, 2, 5, 2});
        // longestCommonPrefix(new String[]{"flower","flow", "flight"});
        longestCommonPrefix2(new String[]{"flower","flow", "flight"});
    }

    static void longestCommonPrefix2(String[] strs) {
        String prefix = strs[0];
        int index = 1;
        while (index < strs.length) {
            for (int i = 0; i < prefix.length(); i++) {
                if (!prefix.equals(strs[index])) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    continue;
                }
                break;
            }
            index++;
        }
        System.out.println(prefix);
    }

    static void longestCommonPrefix(String[] strs) {
        String initialString = strs[0];
        StringBuilder prefix = new StringBuilder();
        boolean isBreak = false;
        for (int j = 0; j < initialString.length(); j++) {
            for (int i = 1; i < strs.length; i++) {
                if (!(prefix.length() < strs[i].length()
                        && initialString.charAt(j) == strs[i].charAt(j))) {
                    isBreak = true;
                    break;
                }
            }
            if(isBreak)
                break;
            prefix.append(initialString.charAt(j));
        }
        System.out.println(prefix);
    }

    /*Input: height = [1,1]
    Output: 49*/
    static void maxAreaInWaterContainer(int[] height) {
        int maxArea = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;
        while (leftPointer <= rightPointer) {
            int h, l;
            if (height[leftPointer] < height[rightPointer]) {
                h = height[leftPointer];
                leftPointer ++;
            } else {
                h = height[rightPointer];
                rightPointer --;
            }
            l = rightPointer - leftPointer + 1;
            maxArea = Math.max(maxArea, h * l);
        }
        System.out.println(maxArea);
    }

    static void palindromeNum(int num) {
        if (num < 0) {
            return;
        }
        int copyNum = num;
        int reversed = 0;
        while (num > 0) {
            int popped = num % 10;
            reversed = reversed * 10 + popped;
            num = num / 10;
        }
        System.out.println(reversed);
        if (copyNum == reversed) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }

    static void reverseInteger (int num) {
        int reverse = 0;
        while (num > 0) {
            int last = num % 10;
            num = num / 10;
            if (reverse > Integer.MAX_VALUE / 10) {
                return;
            }
            reverse = (reverse * 10) + last;
        }
        System.out.println(reverse);
    }

    static void zigzagConversion(String s, int numRows) {
        List<StringBuilder> stringBuilderList = new ArrayList<>();
        for (int i = 0; i < numRows; i ++) {
            stringBuilderList.add(new StringBuilder());
        }
        char[] charArray = s.toCharArray();
        int rowNum = 0;
        boolean goingDown = false;
        for (int i = 0; i < charArray.length; i++) {
            stringBuilderList.get(rowNum).append(s.charAt(i));
            if (rowNum == 0 || rowNum == numRows -1) {
                goingDown = !goingDown;
            }
            if (goingDown) {
                rowNum = rowNum + 1;
            } else {
                rowNum = rowNum - 1;
            }
        }
        System.out.println(stringBuilderList);
    }

    static void longestPalindromicString(String s) {
        int left, right;
        int max = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i ++) {
            left = i;
            right = i; // odd length
            // right = i + 1; // event length
            while (left > -1 && right < chars.length && chars[left] == chars[right]) {
                left--; right++;
            }
            int len = right - left - 1;
            if (len > max) {
                max = len;
                int start = i - len / 2; // even length = i - len / 2 + 1
                int end = i + len / 2;
                System.out.println("Longest Palindrome is " + s.substring(start, end + 1));
            }
        }
    }

    static void longestSubString(String s) {
        char[] charArray = s.toCharArray();
        int left = 0; int right = 0;
        Set<Character> visitedNodes = new HashSet<>();
        while (right >= left && right < charArray.length) {
            if (!visitedNodes.contains(charArray[right])) {
                visitedNodes.add(charArray[right]);
                right ++;
            } else {
                left ++;
                visitedNodes.remove(charArray[left]);
            }
        }
        System.out.println(right - left + 1);
    }

    static Node createLinkedList(int nums[]) {
        Node head = new Node(nums[0]);
        Node current = head;
        for (int i = 1; i < nums.length; i++) {
            current.nextNode = new Node(nums[i]);
            current = current.nextNode;
        }
        System.out.println(head);
        return head;
    }

    static void addTwoNumInLinkedList(Node l1, Node l2) {
        int carry = 0;
        int totalSum = 0;
        Node l1Curr = l1;
        Node l2Curr = l2;
        Node newNode = new Node(0);
        Node curNewNode = newNode;
        while(l1Curr != null || l2Curr != null) {
            int x = (l1Curr != null ? l1Curr.val : 0);
            int y = (l2Curr != null ? l2Curr.val : 0);
            int sum = carry + x + y;
            carry = sum / 10;
            int mod = sum % 10;
            totalSum = (totalSum * 10) + mod;
            curNewNode.nextNode = new Node(mod);
            curNewNode = curNewNode.nextNode;
            l1Curr = l1Curr.nextNode;
            l2Curr = l2Curr.nextNode;
        }
        System.out.println(carry);
        newNode = newNode.nextNode;
        System.out.println(newNode);
        System.out.println(totalSum);
    }

    static void toSumUsingMap(int[] nums, int target) {
        Map<Integer, Integer> twoSum = new HashMap<>();
        for (int i = 0; i< nums.length; i++) {
            System.out.println(twoSum);
            if (!twoSum.containsKey(nums[i])) {
                twoSum.put(target-nums[i], i);
            } else {
                System.out.println(i);
                System.out.println(twoSum.get(nums[i]));
            }
        }
    }


    static void bruteForceSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    System.out.println(i + " : " + j);
                    return;
                }
            }
        }
    }
}


