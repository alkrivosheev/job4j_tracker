package ru.job4j.ood.lsp.parking.actions;

import ru.job4j.ood.lsp.parking.Car;
import ru.job4j.ood.lsp.parking.ParkingLot;
import ru.job4j.ood.lsp.parking.Truck;
import java.util.Scanner;

public class ParkVehicleAction implements UserAction {
    private ParkingLot parkingLot;

    public ParkVehicleAction(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void execute() {
        if (parkingLot == null) {
            System.out.println("Сначала необходимо создать парковку.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите тип машины (1 - легковая, 2 - грузовая): ");
        int type = scanner.nextInt();
        if (type == 1) {
            var car = new Car();
            boolean success = parkingLot.parkVehicle(car);
            if (success) {
                System.out.println("Легковая машина припаркована.");
            } else {
                System.out.println("Нет свободных мест для легковой машины.");
            }
        } else if (type == 2) {
            System.out.print("Введите размер грузовика: ");
            int size = scanner.nextInt();
            var truck = new Truck(size);
            boolean success = parkingLot.parkVehicle(truck);
            if (success) {
                System.out.println("Грузовик припаркован.");
            } else {
                System.out.println("Нет свободных мест для грузовика.");
            }
        } else {
            System.out.println("Некорректный тип машины.");
        }
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}