package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class ParkingLotTest {

    @Test
    public void testParkCarInCarSpot() {
        ParkingLot parkingLot = new ParkingLot(5, 2);
        Vehicle car = new Car();

        boolean result = parkingLot.parkVehicle(car);
        assertTrue(result, "Легковой автомобиль должен быть припаркован.");
    }

    @Test
    public void testParkTruckInTruckSpot() {
        ParkingLot parkingLot = new ParkingLot(5, 2);
        Vehicle truck = new Truck(3);

        boolean result = parkingLot.parkVehicle(truck);
        assertTrue(result, "Грузовик должен быть припаркован на грузовом месте.");
    }

    @Test
    public void testParkTruckInMultipleCarSpots() {
        ParkingLot parkingLot = new ParkingLot(5, 2);
        Vehicle truck = new Truck(3);

        parkingLot.parkVehicle(new Truck(3));
        parkingLot.parkVehicle(new Truck(3));

        boolean result = parkingLot.parkVehicle(truck);
        assertTrue(result, "Грузовик должен быть припаркован на 3 легковых местах подряд.");
    }

    @Test
    public void testParkTruckFailsIfNotEnoughCarSpots() {
        ParkingLot parkingLot = new ParkingLot(3, 1);
        Vehicle truck = new Truck(4);

        boolean result = parkingLot.parkVehicle(truck);
        assertFalse(result, "Грузовик не должен припарковаться, если нет достаточного количества подряд идущих легковых мест.");
    }

    @Test
    public void testParkCarFailsIfNoCarSpots() {
        ParkingLot parkingLot = new ParkingLot(1, 2);
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();

        parkingLot.parkVehicle(car1);

        boolean result = parkingLot.parkVehicle(car2);
        assertFalse(result, "Легковой автомобиль не должен припарковаться, если нет свободных легковых мест.");
    }

}