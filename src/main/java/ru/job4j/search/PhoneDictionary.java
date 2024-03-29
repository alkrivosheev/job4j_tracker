package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        Predicate<Person> compName = n -> n.getName().contains(key);
        Predicate<Person> compSurName = sn -> sn.getSurname().contains(key);
        Predicate<Person> compPhone = cp -> cp.getPhone().contains(key);
        Predicate<Person> compAddress = ca -> ca.getAddress().contains(key);
        var combine = compName.or(compSurName).or(compPhone).or(compAddress);
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}