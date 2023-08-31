package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int leftLn = left.length();
        int rightLn = right.length();
        int minLn = Math.min(leftLn, rightLn);
        for (int i = 0; i < minLn; i++) {
            int asciiOfs1 = (int) left.charAt(i);
            int asciiOfs2 = (int) right.charAt(i);
            if (asciiOfs1 != asciiOfs2) {
                return asciiOfs1 - asciiOfs2;
            }
        }
        if (leftLn != rightLn) {
            return leftLn - rightLn;
        }
        return 0;
    }
}
