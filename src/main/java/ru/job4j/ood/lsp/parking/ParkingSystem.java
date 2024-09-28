package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.actions.CreateParkingLotAction;
import ru.job4j.ood.lsp.parking.actions.ExitAction;
import ru.job4j.ood.lsp.parking.actions.ParkVehicleAction;
import ru.job4j.ood.lsp.parking.actions.UserAction;
import java.util.Scanner;

public class ParkingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAction[] actions = new UserAction[3];
        CreateParkingLotAction createParkingLotAction = new CreateParkingLotAction();
        ParkVehicleAction parkVehicleAction = new ParkVehicleAction(null);
        actions[0] = createParkingLotAction;
        actions[1] = parkVehicleAction;
        actions[2] = new ExitAction();

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать парковку");
            System.out.println("2. Припарковать машину");
            System.out.println("3. Выйти");

            int choice = scanner.nextInt();
            if (choice == 1) {
                actions[0].execute();
                parkVehicleAction.setParkingLot(createParkingLotAction.getParkingLot());
            } else if (choice >= 2 && choice <= 3) {
                actions[choice - 1].execute();
            } else {
                System.out.println("Некорректный выбор.");
            }
        }
    }
}
