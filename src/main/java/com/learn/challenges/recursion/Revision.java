package com.learn.challenges.recursion;

import java.util.ArrayList;
import java.util.List;

public class Revision {
    public static void main(String[] args) {
        generateSubset("abc");
    }

    static void generateSubset(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        generate(result, output, s, 0);
        System.out.println(result);
    }

    static void generate(List<String> result, StringBuilder output, String s, int index) {
        if (index >= s.length()) {
            result.add(output.toString());
            return;
        }

        // include
        output.append(s.charAt(index));
        generate(result, output, s, index + 1);
        output.deleteCharAt(output.length() - 1);

        // exclude
        generate(result, output, s, index + 1);
    }
}
