package ru.job4j.oop;

public class DummyDic {

    public String engToRus(String eng) {
        return "Неизвестное слово. " + eng;
    }

    public static void main(String[] args) {
        DummyDic query = new DummyDic();
        String result = query.engToRus("Hunter");
        System.out.println(result);
    }
}
