package ru.job4j.io;

import java.util.Scanner;
public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int matches = Integer.parseInt(input.nextLine());
            if (matches <= count && 1 <= matches && matches <= 3) {
                turn = !turn;
                count -= matches;
                System.out.println(count + " спичек осталось");
            } else {
                System.out.println("Введенное число должно быть в пределах от 1 до 3 и не более остатка.");
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
