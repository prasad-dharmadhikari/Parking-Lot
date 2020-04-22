package com.bridgelabz.parkinglot.entity;

import com.bridgelabz.parkinglot.utility.ParkingAttendant;

import java.time.LocalTime;

public class ParkingLot {
    public int lotID = 0;

    public ParkingLot(int lotId) {
        this.lotID = lotId;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "lotID=" + lotID +
                '}';
    }
}
