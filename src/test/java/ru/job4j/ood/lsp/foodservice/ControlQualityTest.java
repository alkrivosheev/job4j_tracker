package ru.job4j.ood.lsp.foodservice;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodservice.model.Food;
import ru.job4j.ood.lsp.foodservice.store.Shop;
import ru.job4j.ood.lsp.foodservice.store.Trash;
import ru.job4j.ood.lsp.foodservice.store.Warehouse;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenRedistributeFoodThenItGoesToCorrectStore() {
        Food food = new Food("Milk", LocalDate.now().plusDays(100), LocalDate.now().minusDays(10), 100, 0.2);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        controlQuality.redistribute(food);

        assertTrue(warehouse.getFoodStorage().contains(food));
        assertFalse(shop.getFoodStorage().contains(food));
        assertFalse(trash.getFoodStorage().contains(food));
    }

    @Test
    public void whenShelfLifeLessThan25PercentThenAcceptedInWarehouse() {
        Food food = new Food("Apple", LocalDate.now().plusDays(80), LocalDate.now().minusDays(10), 50, 0);
        Warehouse warehouse = new Warehouse();
        assertTrue(warehouse.accept(food));
    }

    @Test
    public void whenShelfLifeBetween25And75PercentThenAcceptedInShop() {
        Food food = new Food("Bread", LocalDate.now().plusDays(50), LocalDate.now().minusDays(30), 20, 0.1);
        Shop shop = new Shop();
        assertTrue(shop.accept(food));
    }

    @Test
    public void whenShelfLifeMoreThan75PercentThenDiscountAppliedInShop() {
        Food food = new Food("Cheese", LocalDate.now().plusDays(10), LocalDate.now().minusDays(80), 100, 0.2);
        Shop shop = new Shop();
        shop.add(food);
        assertEquals(80, food.getPrice());
    }

    @Test
    public void whenShelfLifeExpiredThenAcceptedInTrash() {
        Food food = new Food("Yogurt", LocalDate.now().minusDays(1), LocalDate.now().minusDays(10), 30, 0);
        Trash trash = new Trash();
        assertTrue(trash.accept(food));
    }

    @Test
    public void whenShelfLifeMoreThan25PercentThenNotAcceptedInWarehouse() {
        Food food = new Food("Milk", LocalDate.now().plusDays(10), LocalDate.now().minusDays(30), 50, 0);
        Warehouse warehouse = new Warehouse();
        assertFalse(warehouse.accept(food));
    }

    @Test
    public void whenShelfLifeLessThan25PercentThenNotAcceptedInShop() {
        Food food = new Food("Orange", LocalDate.now().plusDays(100), LocalDate.now().minusDays(5), 15, 0.05);
        Shop shop = new Shop();
        assertFalse(shop.accept(food));
    }

    @Test
    public void whenShelfLifeNotExpiredThenNotAcceptedInTrash() {
        Food food = new Food("Juice", LocalDate.now().plusDays(20), LocalDate.now().minusDays(10), 10, 0);
        Trash trash = new Trash();
        assertFalse(trash.accept(food));
    }

    @Test
    public void whenShelfLifeLessThan25PercentThenGoesToWarehouse() {
        Food food = new Food("Pasta", LocalDate.now().plusDays(90), LocalDate.now().minusDays(10), 40, 0);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        controlQuality.redistribute(food);

        assertTrue(warehouse.getFoodStorage().contains(food));
        assertFalse(shop.getFoodStorage().contains(food));
        assertFalse(trash.getFoodStorage().contains(food));
    }

    @Test
    public void whenShelfLifeBetween25And75PercentThenGoesToShop() {
        Food food = new Food("Chocolate", LocalDate.now().plusDays(50), LocalDate.now().minusDays(30), 100, 0);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        controlQuality.redistribute(food);

        assertFalse(warehouse.getFoodStorage().contains(food));
        assertTrue(shop.getFoodStorage().contains(food));
        assertFalse(trash.getFoodStorage().contains(food));
    }

    @Test
    public void whenShelfLifeExpiredThenGoesToTrash() {
        Food food = new Food("Butter", LocalDate.now().minusDays(1), LocalDate.now().minusDays(20), 200, 0);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        controlQuality.redistribute(food);

        assertFalse(warehouse.getFoodStorage().contains(food));
        assertFalse(shop.getFoodStorage().contains(food));
        assertTrue(trash.getFoodStorage().contains(food));
    }

    @Test
    public void whenResortThenProductsRedistributedCorrectly() {
        Food milk = new Food("Milk", LocalDate.now().plusDays(90), LocalDate.now().minusDays(10), 50, 0);
        Food cheese = new Food("Cheese", LocalDate.now().plusDays(5), LocalDate.now().minusDays(85), 100, 0.2);
        Food yogurt = new Food("Yogurt", LocalDate.now().minusDays(2), LocalDate.now().minusDays(20), 30, 0);

        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        controlQuality.redistribute(milk);
        controlQuality.redistribute(cheese);
        controlQuality.redistribute(yogurt);
        assertTrue(warehouse.getFoodStorage().contains(milk));
        assertTrue(shop.getFoodStorage().contains(cheese));
        assertTrue(trash.getFoodStorage().contains(yogurt));
        controlQuality.resort();
        assertTrue(warehouse.getFoodStorage().contains(milk));
        assertTrue(shop.getFoodStorage().contains(cheese));
        assertTrue(trash.getFoodStorage().contains(yogurt));
    }

}