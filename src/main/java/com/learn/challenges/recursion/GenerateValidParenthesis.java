package com.learn.challenges.recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateValidParenthesis {

    /*
    All we need to make sure that when
    1. open < 3, then keep on adding brackets (
    2. close < open, then keep on adding )
    3. when count of open == close then return from stack, and add that to result set
     */
    public static void main(String[] args) {
        generateParenthesis(3);
    }

    static void generateParenthesis(int n) {
        List<String> output = new ArrayList<>();
        generate(output, "", n, n);
        System.out.println(output);
    }

    static void generate(List<String> result, String output, int open, int close) {
        if (open == 0 && close == 0) {
            result.add(output);
            return;
        }

        if (open > 0) {
            generate(result, output + "(", open - 1, close);
        }

        if (close > open) {
            generate(result, output + ")", open, close - 1);
        }
    }
}
