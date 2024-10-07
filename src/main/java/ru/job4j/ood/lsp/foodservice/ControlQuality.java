package ru.job4j.ood.lsp.foodservice;

import ru.job4j.ood.lsp.foodservice.model.Food;
import ru.job4j.ood.lsp.foodservice.store.AbstractStore;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<AbstractStore> stores;

    public ControlQuality(List<AbstractStore> stores) {
        this.stores = stores;
    }

    public void redistribute(Food food) {
        for (AbstractStore store : stores) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> allFood = new ArrayList<>();
        for (AbstractStore store : stores) {
            allFood.addAll(store.getFoodStorage());
            store.getFoodStorage().clear();
        }
        for (Food food : allFood) {
            redistribute(food);
        }
    }
}
