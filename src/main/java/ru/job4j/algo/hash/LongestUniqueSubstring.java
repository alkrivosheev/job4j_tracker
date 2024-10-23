package ru.job4j.algo.hash;

import java.util.HashMap;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int start = 0;
        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }
            charIndexMap.put(currentChar, right);
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }
        return str.substring(start, start + maxLength);
    }
}
