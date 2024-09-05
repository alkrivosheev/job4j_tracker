package ru.job4j;

import org.assertj.core.api.Assertions;
import org.junit.Test;


import static org.junit.Assert.*;

public class BattlefieldTest {
    @Test
    public void whenOneVertShip() {
        int[][] input = {
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        };
        Battlefield bf = new Battlefield();
        Assertions.assertThat(bf.countShips(input)).isEqualTo(1);
    }

    @Test
    public void whenTwoShips() {
        int[][] input = {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };
        Battlefield bf = new Battlefield();
        Assertions.assertThat(bf.countShips(input)).isEqualTo(2);
    }

    @Test
    public void whenThreeShips() {
        int[][] input = {
                {0, 1, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };
        Battlefield bf = new Battlefield();
        Assertions.assertThat(bf.countShips(input)).isEqualTo(3);
    }
}