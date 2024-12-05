package com.learn.challenges.leet;

import java.util.ArrayList;
import java.util.List;

public class ZigZagPattern19Nov {
    public static void main(String[] args) {
        convert("PAYPALISHIRING", 3);
    }
        /*
            [
                "PAHN",
                "APLSIIG",
                "YIR"
            ]
         */
    static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();

        // initialize rows with numRows with string builder that will be used
        for (int i = 0; i < Math.min(numRows, s.length()); i ++) {
            rows.add(new StringBuilder());
        }

        boolean goingDown = false;
        int curRow = 0;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if ((curRow == 0) || (curRow == numRows - 1)) {
                goingDown = !goingDown;
            }
            curRow = curRow + (goingDown ? 1 : -1);
        }
        StringBuilder s1 = new StringBuilder();
        for (StringBuilder row : rows) {
            s1.append(row);
        }
        System.out.println(s1);
        return s1.toString();
    }

    /*static void convert(String s, int numRows) {
        int rows = numRows;
        int cols = getColLen(s, rows);
        char[][] matrix = new char[rows][cols];
        int sInc = 0;
        for (int i = 0; i < cols && sInc <= s.length() - 1; i ++) {
            for (int j = 0; j < rows && sInc <= s.length() - 1; j++) {
                if (i % 2 != 0) {
                    if (j == 1) {
                        matrix[j][i] = s.charAt(sInc);
                        sInc ++;
                    }
                } else {
                    matrix[j][i] = s.charAt(sInc);
                    sInc ++;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }*/

    static int getColLen (String s, int rows) {
        int cyclePerLen = 2 * (rows - 1);
        int fullCycles = s.length() / cyclePerLen;
        int fullCyclesRemainder = (s.length() % cyclePerLen) == 0 ? 0  : 1; // if there are more elements left as per total records
        int colLen = (fullCycles * cyclePerLen/ 2) + fullCyclesRemainder;
        System.out.println("Column length " + colLen);
        return colLen;
    }

    /*static void convert(String s, int numRows) {
        int rows = numRows;
        int columns = s.length() / numRows;
        char[][] matrix = new char[rows][columns];
        int index = 0;
        for (int i = 0; i < columns; i ++) {
            for (int j = 0; j < rows; j ++) {
                matrix[j][i] = s.charAt(index);
                index ++;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }*/
}
