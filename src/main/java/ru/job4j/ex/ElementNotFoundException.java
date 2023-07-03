package ru.job4j.ex;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException() {
        System.out.println("Исключение: Строка не найдена.");
    }
}
