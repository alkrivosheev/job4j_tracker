package ruoop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import ru.job4j.oop.Battery;

public class BatteryTest {

    @Test
    public void whenThis30Another50ThenAnotherLoadEqual80() {
        Battery charger = new Battery(30);
        Battery another = new Battery(50);
        charger.exchange(another);
        int expected = 80;
        Assertions.assertThat(another.about()).isEqualTo("My charge: " + expected + "%");
    }

    @Test
    public void whenThis80Another20ThenThisLoadEqual0() {
        Battery charger = new Battery(80);
        Battery another = new Battery(20);
        charger.exchange(another);
        int expected = 0;
        Assertions.assertThat(charger.about()).isEqualTo("My charge: " + expected + "%");
    }

    @Test
    public void whenThis95Another0ThenAnotherLoadEqual80() {
        Battery charger = new Battery(95);
        Battery another = new Battery(0);
        charger.exchange(another);
        int expected = 95;
        Assertions.assertThat(another.about()).isEqualTo("My charge: " + expected + "%");
    }
}