package com.bridgelabz.parkinglot.Observer;

import java.util.HashMap;

public interface Observer {
    public void sendParkingStatus(int currentlyOccupiedSlots, int parkingLotCapacity);
}
