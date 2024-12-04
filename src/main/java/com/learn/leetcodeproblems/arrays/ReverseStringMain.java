package com.learn.leetcodeproblems.arrays;

import java.util.Stack;
import java.util.stream.Collectors;

public class ReverseStringMain {
    public static void main(String[] args) {
        reverseString("ANSHUL");
    }

    //"My Name is Anshul"
    //"luhsnA si emaN yM
    static void reverseString(String s) {
        Stack<Character> reverseChars =
                s.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(Stack::new));

        String reversed = reverseChars.stream()
                .collect(StringBuilder::new,
                        (sb, c) -> {
                            System.out.println(c);
                            sb.insert(0, c);
                        },
                        StringBuilder::append).toString();
        System.out.println(reversed);
    }
}
