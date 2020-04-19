package com.bridgelabz.parkinglot.utility;

import com.bridgelabz.parkinglot.Observer.Owner;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.bridgelabz.parkinglot.service.ParkingLotSystem;

import java.util.HashMap;

public class ParkingAttendant {
    Owner owner = new Owner();
    int parkingLotCapacity = 0;

    public ParkingAttendant(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public HashMap<Integer, String> park(String vehicle, HashMap<Integer, String> parkingLot) throws ParkingLotSystemException {
        Integer emptyParkingSlot = owner.getEmptyParkingSlot(parkingLot);
        if (ParkingLotSystem.isParkingLotFull(parkingLot))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        parkingLot.put(emptyParkingSlot, vehicle);
        return parkingLot;
    }
}
