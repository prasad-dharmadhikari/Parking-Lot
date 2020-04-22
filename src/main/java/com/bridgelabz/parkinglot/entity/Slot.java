package com.bridgelabz.parkinglot.entity;

import java.time.LocalTime;

public class Slot {
    int slotID;
    public LocalTime arrivalTime;
    public LocalTime departureTime;

    public Slot(int slotID, LocalTime arrivalTime) {
        this.slotID = slotID;
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotID=" + slotID +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
