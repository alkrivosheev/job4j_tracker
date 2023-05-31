package ru.job4j.checkstyle;

public class Broken {
    public static final String NEWVALUE = "";
    String name;
    public String surname;
    private int sizeOfEmpty = 10;

    Broken() { }

    void echo() { }

    void media(Object obj) {
        if (obj != null) {
            System.out.println(obj);
        }
    }

    void method(int a, int b, int c, int d, int e, int f, int g) {
    }
}