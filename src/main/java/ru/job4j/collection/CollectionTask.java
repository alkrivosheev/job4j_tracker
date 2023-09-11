package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionTask {
    public static void loop(int[] num1, int[] num2) {
        List<Integer> rez = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < num1.length && j < num2.length) {
            if (num1[i] < num2[j]) {
                rez.add(num1[i]);
                i++;
            } else {
                rez.add(num2[j]);
                j++;
            }
        }
        while (j < num2.length) {
            rez.add(num2[j]);
            j++;
        }
        while (i < num1.length) {
            rez.add(num1[i]);
            i++;
        }
        System.out.println(rez);
    }

    public static void main(String[] args) {
        int[] mass1 = {1, 2, 3, 4};
        int[] mass2 = {3, 4, 5};
        loop(mass1, mass2);
    }
}
