package ru.job4j.ood.lsp.foodservice.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodservice.model.Food;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    @Test
    public void whenFoodShelfLifeLessThan25PercentThenAcceptedInWarehouse() {
        Food food = new Food("Milk", LocalDate.now().plusDays(100), LocalDate.now().minusDays(10), 100, 0);
        Warehouse warehouse = new Warehouse();
        assertTrue(warehouse.accept(food));
    }

}