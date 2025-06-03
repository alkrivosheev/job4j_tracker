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

class ReplaceActionTest {
    @Test
    public void whenItemWasReplacedSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction replaceAction = new ReplaceAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        replaceAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
        );
    }

    @Test
    public void whenItemWasNotReplaced() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        ReplaceAction replaceAction = new ReplaceAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(2);
        when(input.askStr(any(String.class))).thenReturn("New item name");
        replaceAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Edit item ===" + ln
                        + "Ошибка замены заявки." + ln
        );
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Replaced item");
    }
}