package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Поездка.");
    }

    @Override
    public void passengers(int count) {
        System.out.println("Посадка пассажиров.");
    }

    @Override
    public double refuel(double count) {
        System.out.println("Заправка.");
        return 0;
    }
}
