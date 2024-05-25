package ru.job4j.tracker.action;

import ru.job4j.Tracker;
import ru.job4j.tracker.Input;

public interface UserAction {
    String name();

    boolean execute(Input input, Tracker tracker);
}
