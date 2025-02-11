package com.learn.challenges.recursion;

public class SkipCharacter {

    public static void main(String[] args) {
        System.out.println(skip("ansaah", 'a'));
    }

    static String skip(String str, char skipChar) {
        if (str.isEmpty()) {
            return "";
        }
        char firstChar = str.charAt(0);
        if (firstChar == skipChar) {
            return skip(str.substring(1), skipChar);
        } else {
            return firstChar + skip(str.substring(1), skipChar);
        }
    }
}

/*
Building the stack tree
skipchar = a
1. skip(ansaah) -> skip(nsaah)
2. skip(nsaah) -> n + skip(saah)
3. skip(saah) -> s + skip(aah)
4. skip(aah) -> skip(ah)
5. skip(ah) -> skip(h)
6. skip(h) -> h

Unwinding
6. skip(h) = h
5. skip(ah) = h
4. skip(aah) = h
3. skip(saah) = s + h
2. skip(nsaah) = n + s + h
1. skip(ansaah) = n + s + h

 */

