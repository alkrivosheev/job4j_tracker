package ru.job4j.search;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPrioritySecond() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDesc()).isEqualTo("urgent");
    }

    @Test
    public void whenHigherPriorityEquals() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 5));
        queue.put(new Task("middle", 5));
        var result = queue.take();
        assertThat(result.getDesc()).isEqualTo("low");
    }

    @Test
    public void whenHigherPriorityRandom() {
        var queue = new PriorityQueue();
        queue.put(new Task("a", 7));
        queue.put(new Task("b", 4));
        queue.put(new Task("c", 2));
        queue.put(new Task("d", 1));
        queue.put(new Task("e", 6));
        queue.put(new Task("f", 5));
        queue.put(new Task("g", 3));
        queue.put(new Task("h", 2));
        queue.put(new Task("i", 1));
        queue.put(new Task("j", 8));
        var result = queue.take();
        assertThat(result.getDesc()).isEqualTo("d");
    }
}