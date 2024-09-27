package ru.job4j.ood.lsp.parking;

public class ParkingSpot {
    private boolean isOccupied;
    private boolean isTruckSpot;

    public ParkingSpot(boolean isTruckSpot) {
        this.isTruckSpot = isTruckSpot;
        this.isOccupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        if (!isOccupied && !isTruckSpot) {
            return true;
        }
        return !isOccupied && vehicle.getSize() > 1;
    }

    public void park(Vehicle vehicle) {
        if (canFitVehicle(vehicle)) {
            isOccupied = true;
        } else {
            throw new IllegalStateException("Место занято или не подходит для данного автомобиля.");
        }
    }

    public void leave() {
        isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isTruckSpot() {
        return isTruckSpot;
    }
}
