package com.bridgelabz.parkinglot.Observer;

public class AirportPersonnel implements Observer {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    @Override
    public void sendParkingStatus(int currentOccupiedSlots, int parkingLotCapacity) {
        isParkingFull = (currentOccupiedSlots >= parkingLotCapacity)? true : false;
    }
}
