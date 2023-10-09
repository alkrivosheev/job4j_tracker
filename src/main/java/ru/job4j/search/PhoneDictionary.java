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
        Predicate<Person> compName = n -> n.getName().equals(key);
        Predicate<Person> compSurName = sn -> sn.getSurname().equals(key);
        Predicate<Person> compPhone = cp -> cp.getPhone().contains(key);
        Predicate<Person> compAddress = ca -> ca.getAddress().contains(key);
        Predicate<Person> combine = compName.or(compSurName).or(compPhone).or(compAddress);
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}