package ru.job4j.search;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname()).isEqualTo("Arsentev");
    }

    @Test
    public void whenFindLess() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Ilya");
        assertThat(persons.isEmpty()).isTrue();
    }

    @Test
    public void whenFindByAdress() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Debryansky", "6540721245", "Bryansk")
        );
        var persons = phones.find("72");
        assertThat(persons.get(0).getSurname()).isEqualTo("Debryansky");
    }

}