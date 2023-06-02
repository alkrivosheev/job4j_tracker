package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return x - y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        return sum(a) + multiply(a) + minus(a) + divide(a);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int rsl = sum(3);
        System.out.println(rsl);
        rsl = minus(7);
        System.out.println(rsl);
        rsl = calculator.multiply(8);
        System.out.println(rsl);
        rsl = calculator.divide(15);
        System.out.println(rsl);
        rsl = calculator.sumAllOperation(20);
        System.out.println(rsl);
    }
}
