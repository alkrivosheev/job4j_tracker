package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz. ");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            String correctAnswer = getFizzBuzzValue(startAt);
            System.out.println(correctAnswer);
            var userAnswer = input.nextLine();
            startAt++;
            if (!getFizzBuzzValue(startAt).equals(userAnswer)) {
                System.out.println("Ошибка. Начинай снова. ");
                startAt = 0;
            }
            startAt++;
        }
    }

    public static String getFizzBuzzValue(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }
}
