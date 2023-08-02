package ru.job4j.tracker;

import ru.job4j.Item;
import ru.job4j.Tracker;

public class SingleTracker {
    private static Tracker tracker = null;

    private SingleTracker() {
    }

    public static Tracker getInstance() {
        if (tracker == null) {
            tracker = new Tracker();
        }
        return tracker;
    }
}
