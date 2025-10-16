package ru.job4j.tracker;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HbmTrackerTest {
    /**
     * Тестирует метод add.
     * Проверяет, что при добавлении нового элемента он корректно сохраняется в хранилище
     * и может быть найден по его ID.
     * Ожидаемый результат: элемент, найденный по ID после добавления, имеет то же имя,
     * что и исходный добавленный элемент.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    /**
     * Тестирует метод replace.
     * Проверяет, что существующий элемент в хранилище может быть успешно обновлен
     * с новым именем.
     * Ожидаемый результат: метод replace возвращает true, и элемент с указанным ID
     * в хранилище содержит новое имя.
     */
    @Test
    public void whenReplaceItemThenSuccess() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item originalItem = new Item();
            originalItem.setName("original name");
            Item savedItem = tracker.add(originalItem);
            int id = savedItem.getId();
            String updatedName = "updated name";

            Item updatedItem = new Item();
            updatedItem.setName(updatedName);
            boolean replaced = tracker.replace(id, updatedItem);

            Item result = tracker.findById(id);
            assertThat(replaced).isTrue();
            assertThat(result.getName()).isEqualTo(updatedName);
        }
    }

    /**
     * Тестирует метод replace при попытке обновления несуществующего элемента.
     * Проверяет поведение метода replace, когда передается ID, не соответствующий
     * никакому элементу в хранилище.
     * Ожидаемый результат: метод replace возвращает false.
     */
    @Test
    public void whenReplaceNonExistentItemThenFalse() throws Exception {
        try (var tracker = new HbmTracker()) {
            int nonExistentId = 999;
            Item item = new Item();
            item.setName("new name");

            boolean replaced = tracker.replace(nonExistentId, item);

            assertThat(replaced).isFalse();
        }
    }

    /**
     * Тестирует метод delete.
     * Проверяет, что существующий элемент в хранилище может быть успешно удален
     * по его ID.
     * Ожидаемый результат: элемент с указанным ID больше не может быть найден
     * в хранилище (метод findById возвращает null).
     */
    @Test
    public void whenDeleteItemThenSuccess() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("item to delete");
            Item savedItem = tracker.add(item);
            int id = savedItem.getId();

            tracker.delete(id);

            Item result = tracker.findById(id);
            assertThat(result).isNull();
        }
    }

    /**
     * Тестирует метод findAll.
     * Проверяет, что метод возвращает список всех элементов, находящихся в хранилище.
     * Ожидаемый результат: список не пустой, содержит все добавленные элементы
     * с ожидаемыми именами.
     */
    @Test
    public void whenFindAllItemsThenReturnAll() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item();
            item1.setName("item 1");
            Item item2 = new Item();
            item2.setName("item 2");
            Item item3 = new Item();
            item3.setName("item 3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);

            List<Item> allItems = tracker.findAll();

            assertThat(allItems).isNotEmpty();
            assertThat(allItems).hasSize(3);
            assertThat(allItems)
                    .extracting(Item::getName)
                    .containsExactlyInAnyOrder("item 1", "item 2", "item 3");
        }
    }

    /**
     * Тестирует метод findAll при пустом хранилище.
     * Проверяет, что метод возвращает пустой список, если в хранилище нет элементов.
     * Ожидаемый результат: возвращается пустой список.
     */
    @Test
    public void whenFindAllItemsEmptyThenReturnEmptyList() throws Exception {
        try (var tracker = new HbmTracker()) {
            List<Item> allItems = tracker.findAll();

            assertThat(allItems).isEmpty();
        }
    }

    /**
     * Тестирует метод findByName.
     * Проверяет, что метод находит все элементы, чье имя соответствует заданному ключу.
     * Ожидаемый результат: возвращается список, содержащий только элементы с искомым именем.
     */
    @Test
    public void whenFindByNameThenReturnMatchingItems() throws Exception {
        try (var tracker = new HbmTracker()) {
            String searchName = "common name";
            Item item1 = new Item();
            item1.setName(searchName);
            Item item2 = new Item();
            item2.setName("other name");
            Item item3 = new Item();
            item3.setName(searchName);
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);

            List<Item> foundItems = tracker.findByName(searchName);

            assertThat(foundItems)
                    .hasSize(2)
                    .extracting(Item::getName)
                    .allMatch(name -> name.equals(searchName));
        }
    }

    /**
     * Тестирует метод findByName при отсутствии совпадений.
     * Проверяет, что метод возвращает пустой список, если ни один элемент
     * не имеет указанного имени.
     * Ожидаемый результат: возвращается пустой список.
     */
    @Test
    public void whenFindByNameNoMatchThenReturnEmptyList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item();
            item1.setName("existing name");
            tracker.add(item1);

            List<Item> foundItems = tracker.findByName("non-existent name");

            assertThat(foundItems).isEmpty();
        }
    }

    /**
     * Тестирует метод findById.
     * Проверяет, что метод находит существующий элемент по его ID.
     * Ожидаемый результат: возвращается элемент с указанным ID и ожидаемым именем.
     */
    @Test
    public void whenFindByIdThenReturnItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("find me");
            Item savedItem = tracker.add(item);
            int id = savedItem.getId();

            Item result = tracker.findById(id);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(id);
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    /**
     * Тестирует метод findById при поиске несуществующего элемента.
     * Проверяет, что метод возвращает null, если элемент с указанным ID
     * отсутствует в хранилище.
     * Ожидаемый результат: метод findById возвращает null.
     */
    @Test
    public void whenFindByIdNonExistentThenReturnNull() throws Exception {
        try (var tracker = new HbmTracker()) {
            int nonExistentId = 998;

            Item result = tracker.findById(nonExistentId);

            assertThat(result).isNull();
        }
    }
}