package ru.job4j.ood.lsp.parking;

public class ParkingLot {
    private ParkingSpot[] spots;

    public ParkingLot(int carSpots, int truckSpots) {
        spots = new ParkingSpot[carSpots + truckSpots];
        for (int i = 0; i < carSpots; i++) {
            spots[i] = new ParkingSpot(false);
        }
        for (int i = carSpots; i < spots.length; i++) {
            spots[i] = new ParkingSpot(true);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            return parkCar(vehicle);
        } else {
            return parkTruck(vehicle);
        }
    }

    private boolean parkCar(Vehicle car) {
        for (ParkingSpot spot : spots) {
            if (spot.canFitVehicle(car)) {
                spot.park(car);
                return true;
            }
        }
        return false;
    }

    private boolean parkTruck(Vehicle truck) {
        for (ParkingSpot spot : spots) {
            if (spot.isTruckSpot() && spot.canFitVehicle(truck)) {
                spot.park(truck);
                return true;
            }
        }
        return parkTruckInCarSpots(truck);
    }

    private boolean parkTruckInCarSpots(Vehicle truck) {
        int size = truck.getSize();
        int consecutiveSpots = 0;
        for (int i = 0; i < spots.length; i++) {
            if (!spots[i].isTruckSpot() && !spots[i].isOccupied()) {
                consecutiveSpots++;
                if (consecutiveSpots == size) {
                    for (int j = i - size + 1; j <= i; j++) {
                        spots[j].park(truck);
                    }
                    return true;
                }
            } else {
                consecutiveSpots = 0;
            }
        }
        return false;
    }
}
