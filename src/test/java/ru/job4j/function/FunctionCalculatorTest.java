package ru.job4j.function;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionCalculatorTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenQuadroFunctionThenResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(3, 9, x -> 4 * Math.pow(x, 2) - 8 * x);
        List<Double> expected = Arrays.asList(12D, 32D, 60D, 96D, 140D, 192D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenPowFunctionThenResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(1, 6, x -> Math.pow(4, x) - Math.pow(2, x + 1) - 8);
        List<Double> expected = Arrays.asList(-8D, 0D, 40D, 216D, 952D);
        assertThat(result).containsAll(expected);
    }
}