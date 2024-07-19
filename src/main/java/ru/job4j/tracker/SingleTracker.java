package ru.job4j.tracker;

public class SingleTracker {
    private static MemTracker tracker = null;

    private SingleTracker() {
    }

    public static MemTracker getInstance() {
        if (tracker == null) {
            tracker = new MemTracker();
        }
        return tracker;
    }
}
