package com.bridgelabz.parkinglot.Observer;

public interface Observer {
    public void sendParkingStatus(int currentOccupiedSlots, int parkingLotCapacity);
}
