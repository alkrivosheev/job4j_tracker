package ruoop;

import org.junit.jupiter.api.Test;
import ru.job4j.oop.Point;
import ru.job4j.oop.Triangle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class TriangleTest {

    @Test
    public void when00and40and04Then8() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        double expected = 54.6274;
        assertThat(rsl).isCloseTo(expected, offset(0.001));
    }

    @Test
    public void when00and40and40ThenMinus1() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(4, 0);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        double expected = -1;
        assertThat(rsl).isCloseTo(expected, offset(0.001));
    }
}