package ru.job4j;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class Item {
    private int id;
    private String name;

    public LocalDateTime getCreated() {
        return created;
    }

    private LocalDateTime created = now();

    public Item() {

    }

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}