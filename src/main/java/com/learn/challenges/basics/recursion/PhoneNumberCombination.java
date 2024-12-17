package com.learn.challenges.basics.recursion;

import java.util.*;

public class PhoneNumberCombination {

    static Map<String, String> phoneMap = new HashMap<>();

    static {
        phoneMap.put("1", "");
        phoneMap.put("2", "abc");
        phoneMap.put("3", "def");
        phoneMap.put("4", "ghi");
        phoneMap.put("5", "jkl");
        phoneMap.put("6", "mno");
        phoneMap.put("7", "pqrs");
        phoneMap.put("8", "tuv");
        phoneMap.put("9", "wxyz");
    }

    public static void main(String[] args) {
        phoneNumberCombination("");
    }

    static List<String> phoneNumberCombination(String num) {
        List<String> result = new ArrayList<>();
        if (Objects.equals(num, "")) {
            return List.of(" ");
        }
        convert(num, "", 0, result);
        System.out.println(result);
        return result;
    }

    static void convert(String digits, String output, int index, List<String> result) {
        if (index == digits.length()) {
            result.add(output);
            return;
        }
        String letters = phoneMap.get(String.valueOf(digits.charAt(index))); // get phone characters mapping
        for (char c : letters.toCharArray()) {
            convert(digits, output + c, index + 1, result);
        }
    }
}

/*
Building index
1. start from index = 0, that first start making the combination from
    first number, and it's first mapping character values.
   ex - if 2 is the number then it will start from character "a" and then later on with b and c
2. Once the iteration starts at index 0, it picks the value "a", and then increase the index by 1
3. Once the index is increased to 1, then it will pick the second number, and it's first mapping value
    per say this could in combination with a is value "d".
4. Now with recursion, the index value is increased to 2, which is where it will pass "ad" and then start
    unwrapping the recursive call. The output "d" is off the stack now and is added to result.
    AND this is the exit strategy as well
5. This loop is iterated for "e", and then "f"
6. Again the loop with recursion reaches to "b" from "abc", as it completed the recursive calls for "a" only
 */

/*
1. convert(digit = "23", output = "", index = 0, result = [])
2. Run the for loop for 2 which is at index 0 initially
3. convert(digit = "23", output = "" + a, index = 1, result = []) // first recursive outer stack created here
    // the index is 1 so run "for loop" for 3 which is "def"
    // and here the output is set to this stack "a",
    // and index is set to "1" whenever call is returned back to this stack
3.1 convert("23", a + d, 2, []) -> outputs "ad" // first recursive inner stack created here
3.2 convert("23, a + e, 2, []) -> outputs "ae" // second recursive inner stack created here
3.3 convert("23", a + f, 2, []) -> outputs "af" // third recursive inner stack created here
    // No more for loop iterations left so now the "b" is called with index 0 again
    // because before the stack call from "a" the index value was 0
4. convert(digit = "23", output = "" + b, index = 0, result = []) // second recursive outer stack created here
    the process of d, e, f repeats for this
5. convert(digit = "23", output = "" + b, index = 0, result = []) // third recursive stack created here
   the process of d, e, f repeats for this
6. finally the program ends
 */
