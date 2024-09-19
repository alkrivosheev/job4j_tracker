package ru.job4j.ood.ocp;

public class MainShape {

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 4);
        Shape circle = new Circle(3);
        AreaCalculator calculator = new AreaCalculator();
        System.out.println("Area of Rectangle: " + calculator.calculateArea(rectangle));
        System.out.println("Area of Circle: " + calculator.calculateArea(circle));
    }
}
