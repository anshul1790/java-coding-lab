package com.learn.challenges.dsa.arrays;

import java.util.*;

public class NeetCodeArray2 {
    public static void main(String[] args) {
        //groupAnagrams(new String[]{"act","pots","tops","cat","stop","hat"});
        //topKFrequent(new int[]{1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5}, 2);
        //productExceptSelf(new int[]{1, 2, 4, 6});
        /*isValidSudoku(new char[][]{
                {'1', '2', '.', '.', '3', '.', '.', '.', '.'},
                {'4', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '.', '3'},
                {'5', '.', '.', '.', '6', '.', '.', '.', '4'},
                {'.', '.', '.', '8', '.', '3', '.', '.', '5'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '8'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        });*/
        longestConsecutive(new int[]{2,20,4,10,3,4,5});
    }

    /*
    Input: board =
    [["1","2",".",".","3",".",".",".","."],
     ["4",".",".","5",".",".",".",".","."],
     [".","9","8",".",".",".",".",".","3"],
     ["5",".",".",".","6",".",".",".","4"],
     [".",".",".","8",".","3",".",".","5"],
     ["7",".",".",".","2",".",".",".","6"],
     [".",".",".",".",".",".","2",".","."],
     [".",".",".","4","1","9",".",".","8"],
     [".",".",".",".","8",".",".","7","9"]]
    Output: true
     */
    public static boolean isValidSudoku(char[][] board) {
        List<Set<Character>> rows = new ArrayList<>();
        List<Set<Character>> columns = new ArrayList<>();
        List<Set<Character>> boxes = new ArrayList<>();

        for (int i = 0; i < 9 ; i++) {
            rows.add(new HashSet<>());
            columns.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentChar = board[i][j];
                if (currentChar == '.')
                    continue;
                int boxIndex = (i / 3) * 3 + (j / 3);
                if (rows.get(i).contains(currentChar) || columns.get(j).contains(currentChar)
                        || boxes.get(boxIndex).contains(currentChar)) {
                    System.out.println("This is not valid sudoku");
                    return false;
                }
                rows.get(i).add(currentChar);
                columns.get(j).add(currentChar);
                boxes.get(boxIndex).add(currentChar);
            }
        }
        return true;
    }

    /*
    Input: strs = ["act","pots","tops","cat","stop","hat"]
    Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
    a=0;c=2;t=17
    c=2;a=0;t=17
     */
    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashedStrings = new HashMap<>();
        for (String s : strs) {
            int[] keyArr = new int[26];
            for (char c : s.toCharArray()) {
                keyArr[c - 'a']++;
            }
            StringBuilder key = new StringBuilder();
            for (int count : keyArr) {
                key.append(count).append("#");
            }
            hashedStrings.computeIfAbsent(
                    String.valueOf(key), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(hashedStrings.values());
    }

    /*
    Input: nums = [1,2,2,3,3,3], k = 2
    Output: [2,3]
    */
    static public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        Queue<Map.Entry<Integer, Integer>> freqMapMaxHeap = new PriorityQueue<>(
                (o1, o2) -> o2.getValue() - o1.getValue());
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        freqMapMaxHeap.addAll(freqMap.entrySet());
        int[] topElements = new int[2];
        for (int i = 0; i < k; i++) {
            System.out.println(freqMapMaxHeap.peek());
            topElements[i] = Objects.requireNonNull(freqMapMaxHeap.poll()).getKey();
        }
        System.out.println(Arrays.toString(topElements));
        return topElements;
    }

    /*
        Input: nums = [1,2,4,6]
        Output: [48,24,12,8]

        prefix = [1,1,2,8]
        suffix = [48,24,6,1]
    */
    static public int[] productExceptSelf(int[] nums) {
        int[] prefixes = new int[nums.length];
        int[] suffixes = new int[nums.length];
        int[] productArr = new int[nums.length];
        int product = 1;
        prefixes[0] = product;
        suffixes[nums.length - 1] = product;
        for (int i = 1; i < nums.length; i ++) {
            prefixes[i] = prefixes[i-1] * nums[i-1];
        }
        for (int j = suffixes.length - 1; j > 0; j--) {
            productArr[j] = suffixes[j] * prefixes[j];
            suffixes[j - 1] = suffixes[j] * nums[j];
        }
        productArr[0] = prefixes[0] * suffixes[0];
        System.out.println(Arrays.toString(productArr));
        return null;
    }

    /*
    Input: nums = [2,20,4,10,3,1,4,5]
    Output: 4
    */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> uniqueSet = new HashSet<>();
        for (int num : nums) {
            uniqueSet.add(num);
        }
        int maxSeq = 0;
        for (Integer num : uniqueSet) {
            int seqStart = num - 1;
            if (!uniqueSet.contains(seqStart)) {
                int currentNum = num;
                int currentSeq = 0;
                while (uniqueSet.contains(currentNum)) {
                    currentSeq ++;
                    currentNum ++;
                }
                maxSeq = Math.max(maxSeq, currentSeq);
            }
        }
        System.out.println(maxSeq);
        return maxSeq;
    }
}
