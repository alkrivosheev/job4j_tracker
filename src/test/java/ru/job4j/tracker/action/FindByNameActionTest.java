package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import ru.job4j.tracker.*;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

class FindByNameActionTest {

    @Test
    public void whenItemsFound() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item1 = tracker.add(new Item("Test item"));
        Item item2 = tracker.add(new Item("Test item"));
        tracker.add(new Item("Other item"));
        FindByNameAction action = new FindByNameAction(output);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Test item");
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item1 + ln
                        + item2 + ln
        );
    }

    @Test
    public void whenItemsNotFound() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Existing item"));
        FindByNameAction action = new FindByNameAction(output);
        Input input = mock(Input.class);
        String searchName = "Non-existent";
        when(input.askStr(any(String.class))).thenReturn(searchName);
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + "Заявки с именем: " + searchName + " не найдены." + ln
        );
    }

    @Test
    public void whenMultipleItemsFound() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item1 = tracker.add(new Item("Duplicate"));
        Item item2 = tracker.add(new Item("Duplicate"));
        Item item3 = tracker.add(new Item("Duplicate"));
        FindByNameAction action = new FindByNameAction(output);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Duplicate");
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item1 + ln
                        + item2 + ln
                        + item3 + ln
        );
    }

    @Test
    public void testName() {
        Output output = new StubOutput();
        FindByNameAction action = new FindByNameAction(output);
        assertThat(action.name()).isEqualTo("Find items by name");
    }

}