package ru.job4j.cast;

public class VehicleUsage {
    public static void main(String[] args) {
        Vehicle plane = new Airplane();
        Vehicle train = new Train();
        Vehicle bus = new Bus();

        Vehicle[] vehicles = new Vehicle[]{plane, train, bus};
        for (Vehicle v : vehicles) {
            v.move();
        }
    }
}
