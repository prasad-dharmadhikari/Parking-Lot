package com.bridgelabz.parkinglot.Observer;

import com.bridgelabz.parkinglot.service.ParkingLotSystem;

import java.util.HashMap;

public class AirportPersonnel implements Observer {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    @Override
    public void sendParkingStatus(HashMap<Integer, String> parkingLot) {
        isParkingFull = (ParkingLotSystem.isParkingLotFull(parkingLot))? true : false;
    }
}
