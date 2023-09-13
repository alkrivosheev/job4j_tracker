package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = value.split("/", 2)[0];
            for (String el : value.split("/")) {
                if (el.equalsIgnoreCase("k1") || el.equalsIgnoreCase("k2")) {
                    tmp.add(start);
                } else if (el.equalsIgnoreCase("sk1") || el.equalsIgnoreCase("sk2")) {
                    start = start + "/" + el;
                    tmp.add(start);
                } else {
                    tmp.add(start + "/" + el);
                }
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs);
    }

    public static void sortDesc(List<String> orgs) {
        Comparator<String> cmp = new DepDescComp();
        Collections.sort(orgs, cmp);
    }
}
