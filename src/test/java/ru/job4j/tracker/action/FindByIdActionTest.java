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

class FindByIdActionTest {

    @Test
    public void whenItemFound() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Found item"));
        FindByIdAction action = new FindByIdAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemNotFound() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Existing item"));
        FindByIdAction action = new FindByIdAction(output);
        Input input = mock(Input.class);
        int nonExistentId = 999;
        when(input.askInt(any(String.class))).thenReturn(nonExistentId);
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + "Заявка с введенным id: " + nonExistentId + " не найдена." + ln
        );
    }

    @Test
    public void testName() {
        Output output = new StubOutput();
        FindByIdAction action = new FindByIdAction(output);
        assertThat(action.name()).isEqualTo("Find item by id");
    }

}