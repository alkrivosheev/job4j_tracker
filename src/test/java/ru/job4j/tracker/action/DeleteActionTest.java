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

class DeleteActionTest {
    @Test
    public void whenItemWasDeletedSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Item to delete"));
        DeleteAction deleteAction = new DeleteAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
        );
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenItemWasNotDeleted() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Remaining item"));
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(999);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
        );
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void testName() {
        Output output = new StubOutput();
        DeleteAction deleteAction = new DeleteAction(output);
        assertThat(deleteAction.name()).isEqualTo("Delete item");
    }
}