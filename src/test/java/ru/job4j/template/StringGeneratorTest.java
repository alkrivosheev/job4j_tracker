package ru.job4j.template;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class StringGeneratorTest {

    @Test
    void whenCorrectTemplateThenReturnReplacedString() {
        Generator generator = new StringGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "John");
        args.put("subject", "Java");
        String template = "Hello, {name}. Welcome to {subject}!";
        String result = generator.produce(template, args);
        assertThat("Hello, John. Welcome to Java!").isEqualTo(result);
    }

    @Test
    void whenMissingKeyThenThrowException() {
        Generator generator = new StringGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "John");
        String template = "Hello, {name}. Welcome to {subject}!";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, args);
        });
        assertEquals("No value provided for key: subject", exception.getMessage());
    }

    @Test
    void whenUnmatchedBracketThenThrowException() {
        Generator generator = new StringGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "John");
        String template = "Hello, {name. Welcome to {subject}!";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, args);
        });
        assertTrue(exception.getMessage().contains("Unmatched opening bracket"));
    }

}