package ru.job4j.collection;

import java.util.HashSet;
public class UniqueText {
    public boolean isEquals(String originText, String duplicateText) {
        int counter = 0;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String word : origin) {
            check.add(word);
        }
        for (String word : text) {
            if (check.contains(word)) {
                counter++;
            }
        }
        return origin.length == counter;
    }
}
