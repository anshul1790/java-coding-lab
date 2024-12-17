package com.learn.challenges.basics.recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubSets {

    public static void main(String[] args) {
        String input = "abc";
        generateSubString(input);
    }

    public static void generateSubString(String s) {
        StringBuilder output = new StringBuilder();
        int index = 0;
        List<String> answer = new ArrayList<>();
        solve(s, output, index, answer);
        System.out.println(answer);
    }

    public static void solve(String s, StringBuilder output, int index, List<String> answer) {
        if (index >= s.length()) {
            answer.add(output.toString());
            return;
        }

        // include call
        char c = s.charAt(index);
        output.append(c);
        solve(s, output, index + 1, answer);
        output.deleteCharAt(output.length() - 1);


        // exclude call
        solve(s, output, index + 1, answer);

    }
}