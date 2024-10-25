package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickListTest {

    @Test
    void testQuickSortWithIntegers() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5));
        QuickList.quickSort(list, Comparator.naturalOrder());
        List<Integer> expected = Arrays.asList(1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9);
        assertEquals(expected, list);
    }

    @Test
    void testQuickSortWithDuplicates() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 5, 3, 5, 3, 5, 3));
        QuickList.quickSort(list, Comparator.naturalOrder());
        List<Integer> expected = Arrays.asList(3, 3, 3, 3, 5, 5, 5, 5);
        assertEquals(expected, list);
    }

    @Test
    void testQuickSortWithCustomObjects() {
        class Person {
            String name;
            int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return name + "(" + age + ")";
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Person person = (Person) o;
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }

        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35),
                new Person("Alice", 22)
        ));
        QuickList.quickSort(list, Comparator.comparingInt(p -> p.age));
        List<Person> expected = Arrays.asList(
                new Person("Alice", 22),
                new Person("Bob", 25),
                new Person("Alice", 30),
                new Person("Charlie", 35)
        );
        assertEquals(expected, list, "The list should be sorted by age.");
    }

    @Test
    void whenSortThenOk() {
        User user1 = new User(1, "11");
        User user2 = new User(1, "22");
        User user3 = new User(3, "33");
        User user4 = new User(-4, "44");
        User user5 = new User(4, "55");
        User user6 = new User(6, "66");
        Comparator<User> comparator = Comparator.comparingInt(User::getId);
        List<User> listUser = new ArrayList<>();
        listUser.add(user3);
        listUser.add(user5);
        listUser.add(user1);
        listUser.add(user4);
        listUser.add(user6);
        listUser.add(user2);
        QuickList.quickSort(listUser, comparator);
        List<Integer> result = listUser.stream().map(User::getId).toList();
        assertThat(result).containsExactly(-4, 1, 1, 3, 4, 6);
    }

    @Test
    void whenReverseOrderThenOk() {
        Comparator<Integer> comparator = (a, b) -> b - a;
        List<Integer> list = new ArrayList<>();
        list.add(-2);
        list.add(-8);
        list.add(1);
        list.add(0);
        list.add(10);
        list.add(1);
        list.add(8);
        QuickList.quickSort(list, comparator);
        System.out.println(list);
        assertThat(list).containsExactly(10, 8, 1, 1, 0, -2, -8);
    }

    private static class User {
        private Integer id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }
    }
}