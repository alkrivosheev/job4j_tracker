package ru.job4j.collection;

import java.util.Set;
import java.util.TreeSet;

public class CollectionTask {
    public static void main(String[] args) {
        int[] mass1 = {4, 3, 2, 1};
        int[] mass2 = {4, 5};
        Set<Integer> resMass = new TreeSet<>();
        for (int num : mass1) {
            resMass.add(num);
        }
        for (int num : mass2) {
            resMass.add(num);
        }
        System.out.println(resMass);
    }
}
