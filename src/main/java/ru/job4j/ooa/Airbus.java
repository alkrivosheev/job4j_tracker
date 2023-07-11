package ru.job4j.ooa;

public final class Airbus extends Aircraft {
    private static int countEngine = 2;

    private String name;

    public Airbus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printModel() {
        System.out.println("Модель самолета: " + name);
    }

    public void printCountEngine() {
        int ce = this.name.equals("A380") ? countEngine + 2 : countEngine;
        System.out.println("Количество двигателей равно: " + ce);
    }

    @Override
    public String toString() {
        return "Airbus{"
                + "name='" + name + '\''
                + '}';
    }
}