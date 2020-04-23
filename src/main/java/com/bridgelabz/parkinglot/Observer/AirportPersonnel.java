package com.bridgelabz.parkinglot.Observer;

public class AirportPersonnel implements Observer {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    @Override
    public void sendParkingStatus(int currentlyOccupiedSlots, int parkingLotCapacity) {
        isParkingFull = (currentlyOccupiedSlots == parkingLotCapacity)? true : false;
    }
}
