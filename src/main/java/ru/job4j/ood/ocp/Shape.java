package ru.job4j.ood.ocp;

public class Shape {
    public String type;
}

class Rectangle extends Shape {
    public double length;
    public double width;

    public Rectangle(double length, double width) {
        this.type = "Rectangle";
        this.length = length;
        this.width = width;
    }
}

class Circle extends Shape {
    public double radius;

    public Circle(double radius) {
        this.type = "Circle";
        this.radius = radius;
    }
}

class AreaCalculator {

    public double calculateArea(Shape shape) {
        if (shape.type.equals("Rectangle")) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.length * rectangle.width;
        } else if (shape.type.equals("Circle")) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        }
        return 0;
    }
}

