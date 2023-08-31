package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int leftInt, rightInt;
        String[] words;
        words = left.split(". ");
        leftInt = Integer.parseInt(words[0]);
        words = right.split(". ");
        rightInt = Integer.parseInt(words[0]);
        return Integer.compare(leftInt, rightInt);
    }
}
