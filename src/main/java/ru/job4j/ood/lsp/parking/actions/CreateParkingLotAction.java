package ru.job4j.ood.lsp.parking.actions;

import ru.job4j.ood.lsp.parking.ParkingLot;

import java.util.Scanner;

public class CreateParkingLotAction implements UserAction {
    private ParkingLot parkingLot;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество мест для легковых машин: ");
        int carSpots = scanner.nextInt();
        System.out.print("Введите количество мест для грузовых машин: ");
        int truckSpots = scanner.nextInt();

        parkingLot = new ParkingLot(carSpots, truckSpots);
        System.out.println("Парковка создана с " + carSpots + " мест для легковых машин и " + truckSpots + " мест для грузовых машин.");
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
}