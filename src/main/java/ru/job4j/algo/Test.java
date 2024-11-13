package ru.job4j.algo;

import java.util.HashSet;
import java.util.Set;

/*Дан массив чисел,
* нужно найти индекс
* первого повторяющегося
*  значения
*/
public class Test {
    public static int findFirstDuplicateIndex(int[] data) {
        Set<Integer> seenNumbers = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            if (!seenNumbers.add(data[i])) {
                return i; // Возвращаем индекс первого повтора
            }
        }
        return -1; // Если повторяющихся чисел нет, возвращаем -1
    }

    public static void main(String[] args) {
        int[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int index = findFirstDuplicateIndex(data);
        System.out.println(index); // Выводит 3, так как число 1 повторяется впервые на индексе 3
    }
}
