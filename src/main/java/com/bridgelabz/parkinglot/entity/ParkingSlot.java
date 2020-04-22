package com.bridgelabz.parkinglot.entity;

import java.time.LocalTime;

public class ParkingSlot {
    int slotId;
    LocalTime time;

    public ParkingSlot(int slotId, LocalTime time) {
        this.slotId = slotId;
        this.time = time;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}
