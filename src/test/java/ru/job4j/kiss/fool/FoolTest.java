package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FoolTest {

    @Test
    void whenNumberDivisibleBy3And5ThenFizzBuzz() {
        assertEquals("FizzBuzz", Fool.getFizzBuzzValue(15));
        assertEquals("FizzBuzz", Fool.getFizzBuzzValue(30));
        assertEquals("FizzBuzz", Fool.getFizzBuzzValue(45));
    }

    @Test
    void whenNumberDivisibleBy3ThenFizz() {
        assertEquals("Fizz", Fool.getFizzBuzzValue(3));
        assertEquals("Fizz", Fool.getFizzBuzzValue(9));
        assertEquals("Fizz", Fool.getFizzBuzzValue(18));
    }

    @Test
    void whenNumberDivisibleBy5ThenBuzz() {
        assertEquals("Buzz", Fool.getFizzBuzzValue(5));
        assertEquals("Buzz", Fool.getFizzBuzzValue(10));
        assertEquals("Buzz", Fool.getFizzBuzzValue(20));
    }

    @Test
    void whenNumberNotDivisibleBy3Or5ThenNumber() {
        assertEquals("1", Fool.getFizzBuzzValue(1));
        assertEquals("2", Fool.getFizzBuzzValue(2));
        assertEquals("4", Fool.getFizzBuzzValue(4));
    }
}
