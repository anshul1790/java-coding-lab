package com.learn.challenges.basics.recursion;

public class ReverseStringR {

    public static void main(String[] args) {
        System.out.println(reverseString("hello"));
    }

    static String reverseString(String n) {
        if (n.length() <= 1)
            return n;
        return reverseString(n.substring(1)) + n.charAt(0);
    }
}

/*
Stack building
1. rs("hello") = rs(ello) + h
2. rs("ello") = rs("llo") + e
3. rs("llo") = rs("lo") + l
4. rs("lo") = rs("o") + l
5. rs("o") = o
// at every stack call it has reference to it's local values like, o, l, l... etc

Stack unwinding
rs("o") -> "o"
rs("lo") -> o + l
rs(llo) -> o + l + l
rs(ello) -> o + l + l + e
rs(hello) -> o + l + l + e + h

 */