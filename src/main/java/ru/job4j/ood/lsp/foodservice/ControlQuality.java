package ru.job4j.ood.lsp.foodservice;

import ru.job4j.ood.lsp.foodservice.model.Food;
import ru.job4j.ood.lsp.foodservice.store.AbstractStore;

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
}
