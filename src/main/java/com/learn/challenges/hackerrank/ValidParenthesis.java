package com.learn.challenges.hackerrank;

import java.util.*;

public class ValidParenthesis {

    public static void main(String[] args) {
        validParenthesis("]");
    }

    static void validParenthesis(String parenthesis) {
        boolean isValid = true;

        Stack<Character> openingBrackets = new Stack<>();
        Map<Character, Character> openCloseBracketMap = new HashMap<>();
        openCloseBracketMap.put('}', '{');
        openCloseBracketMap.put(')', '(');
        openCloseBracketMap.put(']', '[');

        for (int i  = 0; i < parenthesis.length(); i++) {
            char currentChar = parenthesis.charAt(i);
            if (currentChar == '[' ||
                    currentChar == '{' ||
                    currentChar == '(') {
                openingBrackets.add(currentChar);
            } else if (currentChar == ']' || currentChar == '}' || currentChar == ')') {
                if (openingBrackets.empty()) {
                    isValid = false;
                    break;
                }
                if (openCloseBracketMap.get(currentChar) != openingBrackets.pop()) {
                    isValid = false;
                    break;
                }
            }
        }
        if (!openingBrackets.empty()) {isValid = false;}
        System.out.println(isValid);
    }
}

/*

"{}()", "[{()}]", "({()})

opening parenthesis =
1. create a string of valid parenthesis -> {([
2. for each input par string compare using indexOf
3. if it's opening bracket then add in hashSet
4. if it's closing bracket then pop element from Set
5. this popped element and the incoming close bracked should match

 */
