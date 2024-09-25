package ru.job4j.ood.lsp.foodservice.store;

import ru.job4j.ood.lsp.foodservice.model.Food;

public class Trash extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        return food.getShelfLifePercentage() >= 100;
    }
}
