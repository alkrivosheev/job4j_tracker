package ru.job4j.lombok;

import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @Getter
    @EqualsAndHashCode.Include
    private int id;

    @Getter
    @Setter
    private String name;

    public Category(int id) {
        this.id = id;
    }
}