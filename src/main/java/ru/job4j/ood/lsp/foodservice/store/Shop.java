package ru.job4j.ood.lsp.foodservice.store;

import ru.job4j.ood.lsp.foodservice.model.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        double percentage = food.getShelfLifePercentage();
        if (percentage >= 25 && percentage <= 75) {
            return true;
        } else if (percentage > 75 && percentage < 100) {
            food.setPrice(food.getPrice() * (1 - food.getDiscount()));
            return true;
        }
        return false;
    }
}
