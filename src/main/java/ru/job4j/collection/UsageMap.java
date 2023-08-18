package ru.job4j.collection;

import java.util.HashMap;
public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("kassandr@yandex.ru", "Станислав Кейлогеров");
        map.put("muslimfav@mail.ru", "Магомаев Ахмед");
        map.put("grouvhorizen@yandex.ru", "Белокопытов Арсений");
        map.put("muslimfav@mail.ru", "Магомаев Ахмадиниджон");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
