package ru.job4j.ood.lsp.foodservice.store;

import ru.job4j.ood.lsp.foodservice.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore {
    protected List<Food> foodStorage = new ArrayList<>();

    public abstract boolean accept(Food food);

    public void add(Food food) {
        if (accept(food)) {
            foodStorage.add(food);
        }
    }

    public List<Food> getFoodStorage() {
        return foodStorage;
    }
}

