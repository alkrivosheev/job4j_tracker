package ru.job4j.tracker;

import ru.job4j.Tracker;

public interface UserAction {
    String name();

    boolean execute(Input input, Tracker tracker);
}
