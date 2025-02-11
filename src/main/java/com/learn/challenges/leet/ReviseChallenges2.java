package com.learn.challenges.leet;

import java.util.*;

public class ReviseChallenges2 {

    static class ReviseListNode {
        int val;
        ReviseListNode next;
        public ReviseListNode(int val) {
            this.val = val;
            this.next = null;
        }

        @Override
        public String toString() {
            return "ReviseListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        //twoSumRevise(new int[]{3, 3}, 6);
        /*ReviseListNode l1 = createLinkedList(new int[]{2, 4, 3});
        ReviseListNode l2 = createLinkedList(new int[]{5, 6, 4});
        addTwoNumbers(l1, l2);*/
        //lengthOfLongestSubstring("abcabcbb");
        //longestPalindromicString("babad");
        //zigzagPattern("PAYPALISHIRING", 3);
        // reverseInteger(123);
        //maxArea(new int[]{1, 3, 4, 2, 5, 2});
        //longestCommonPrefix(new String[]{"flower","flow","floght"});
        threeSum(new int[]{0, 0, 0, 0});
    }

    /*
        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Explanation:
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
        The distinct triplets are [-1,0,1] and [-1,-1,2].
        Notice that the order of the output and the order of the triplets does not matter.

        Need to use two pointer approach
        1. sort the array [-4, -1, -1, 0, 1, 2]
        2. Start from initial index = i;; This needs to be done till index reaches maxArrayLen
        3. Initialise the pointers with left = index and right = arrayLength
            This needs to be done in the loop till left overlaps with
            right to mark for no more combinations left try
        4. Start finding the sum = 0, using indexPos, leftPos, rightPos of array
        5. if the sum < 0, then move towards right by left++; else move towards left by right--
    */
    static void threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int index = 0; index < nums.length - 2; index++) {
            int left = index + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                if (sum < 0) {
                    left ++;
                } else if (sum > 0) {
                    right --;
                } else {
                   triplets.add(List.of(nums[index], nums[left], nums[right]));
                    left ++;
                    right --;
                }
            }
        }
        System.out.println(triplets);
    }

    /*
    Input: strs = ["flower","flow","flight"]
    Output: "fl"

    1. Start iterating till end from index = 1 i.e. flow
    2. Loop through character length of flower
    3. Create prefix initially as flower, and then update this prefix
    4. start removing from character from right from flower -> flowe -> flow
    5. When you reach at flow then stop, and repeat this for next index = 2

     */
    static void longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String currentString = strs[i];
            while (!prefix.isEmpty()) {
                if (currentString.indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length()-1);
                    continue;
                }
                break;
            }
        }
        System.out.println(prefix);
    }

    /*
    Input: nums = [2,7,11,15], target = 9
    1. Use the map to store the difference and position of each index of that calculation
    2. For each item in map, we will find the value at index and keys at Map
    3. example
        1. {}
        2. find 2 and it doesnt exist -> {7,0}
        3. find 7 and it exists so break loop, and returns current index and index where it found 7
     */
    static void twoSumRevise(int[] arr, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int targetValue = target - arr[i];
            if (sumMap.get(arr[i]) != null) {
                System.out.printf("The indexes are %d and %d", sumMap.get(arr[i]), i);
                break;
            }
            sumMap.put(targetValue, i);
        }
    }

    /*
    Input linked list
    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
        Explanation: 342 + 465 = 807.
     */

    static ReviseListNode createLinkedList(int[] num) {
        ReviseListNode node = new ReviseListNode(0);
        ReviseListNode current = node;
        for (int i = 0; i < num.length; i++) {
            current.next = new ReviseListNode(num[i]);
            current = current.next;
        }
        node = node.next;
        return node;
    }

    /*
    1.Need to run the loop while l1 and l2 is not null
    2.pick the next value if the node is not null
    3. define initial carry to 0
    4. sum the l1.val + l2.val + carry
    5. assign its value to some arraylist by only considering the value divided by 10;
    6. Also update the carry with modulo of 10
    7. repeats this while l1 and l2 is not null

     */
    static void addTwoNumbers(ReviseListNode l1, ReviseListNode l2) {
        ReviseListNode nodeSum = new ReviseListNode(0);
        ReviseListNode head = nodeSum;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x1 = l1 != null ? l1.val : 0;
            int x2 = l2 != null ? l2.val : 0;
            int tempSum = x1 + x2 + carry;
            carry = tempSum / 10;
            int finalVal = tempSum % 10;
            head.next = new ReviseListNode(finalVal);
            l1 = l1.next;
            l2 = l2.next;
            head = head.next;
        }
        if (carry > 0) {
            head.next = new ReviseListNode(carry);
        }
        nodeSum = nodeSum.next;
        System.out.println(nodeSum);
    }

    /*
    [abcabcbb]
    1. initialise the two pointers using left = 0 and right = 0
    2. Utilise a SET to check whether the Char exists or not for every new index
    3. If Char doesn't exist keep on adding character to SET and increase right pointer
    4. If Character exist in SET already then increase the left pointer
    5. At each iteration, we will calculate the size of map and compare it with prev value
     */
    static void lengthOfLongestSubstring(String s) {
        Set<Character> uniqueStringSet = new HashSet<>();
        int max = 0;
        int left = 0, right = 0;
        while (left <= right && right < s.length()) {
            char currentVal = s.charAt(right);
            if (!uniqueStringSet.contains(currentVal)) {
                uniqueStringSet.add(currentVal);
                max = Math.max(max, uniqueStringSet.size());
                right ++;
            } else {
                uniqueStringSet.remove(s.charAt(left));
                left ++;
            }
        }
        System.out.println(max);
    }

    /*
    Input: s = "babad"
    Output: "bab"
    Explanation: "aba" is also a valid answer.

    1. start the loop from index value 0
    2. Run the internal loop from index value moving left till 0 and towards till right
    3. The value at each pos should character position should match
    */
    static void longestPalindromicString(String s) {
        int max = 0;
        String longestSubstr;
        for (int i = 0; i < s.length()-1; i ++) {
            int left = i;
            int right = i;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left --;
                right ++;
            }
            int len = right - left - 1;
            if (len > max) {
                max = len;
                int start = i - len / 2; // even length = i - len / 2 + 1
                int end = i + len / 2;
                longestSubstr = s.substring(start, end + 1);
                System.out.println("longestSubstr " + longestSubstr);
            }
        }

    }

    /*
    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
    (you may want to display this pattern in a fixed font for better legibility)
        P   A   H   N
        A P L S I I G
        Y   I   R
    And then read line by line: "PAHNAPLSIIGYIR"
    > goingDown = false
    > loop through the entire string
    > when index = 0, index = numrows then update goingDown = true
    > Update the below array update array when goingDown
      index = goingDown ? index + 1 (increment)
     > when index = numRoaws then update goingDown = false
      index = !goingDown ? index - 1 (decrement)
    */
    static void zigzagPattern(String s, int numRows) {
        List<List<Character>> zigzagPattern = new ArrayList<>(3);

        zigzagPattern.add(new ArrayList<>());
        zigzagPattern.add(new ArrayList<>());
        zigzagPattern.add(new ArrayList<>());

        int zigzagIndex = 0;
        boolean goingDown = false;
        for (int i = 0; i < s.length(); i++) {
            if (zigzagIndex == 0 || zigzagIndex == numRows - 1) {
                goingDown = !goingDown;
            }
            zigzagPattern.get(zigzagIndex).add(s.charAt(i));
            zigzagIndex = goingDown ? zigzagIndex + 1 : zigzagIndex - 1;
        }
        System.out.println(zigzagPattern);
    }

    /*Input: x = 123
    Output: 321
    */
    static void reverseInteger(int num) {
        int reverse = 0;
        while (num > 0) {
            int mod = num % 10;
            if (reverse > Integer.MAX_VALUE / 10) {
                System.out.println("Number to exceed is exceeding the integer length");
                break;
            }
            reverse = (reverse * 10) + mod;
            num = num / 10;
        }
        System.out.println(reverse);
    }

    /*
    Input: height = [1,8,6,2,5,4,8,3,7]
    Output: 49
    Explanation: The above vertical lines are represented by array
     [1,8,6,2,5,4,8,3,7].
     In this case, the max area of water (blue section) the container can contain is 49.

     1. Use two pointer approach, left = 0 and right = maxLen
     2. compare the values at left and right
     3. If the value at left is small then move right by doing left ++ else move left that is by right --
     4. At each move calculate the max area by
        Difference between right - left indices
        multiply with
        smallest of value at left or right
      5. repeat this loop until left overlaps with right
     */
    static void maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left <= right) {
            int length = right - left;
            int breadth;
            if (height[left] < height[right]) {
                breadth = height[left];
                left ++;
            } else {
                breadth = height[right];
                right --;
            }
            max = Math.max(length * breadth, max);
        }
        System.out.println(max);
    }
}
