package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.Observer.AirportPersonnel;
import com.bridgelabz.parkinglot.Observer.Owner;
import com.bridgelabz.parkinglot.entity.ParkingLot;
import com.bridgelabz.parkinglot.entity.Slot;
import com.bridgelabz.parkinglot.entity.Vehicle;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.bridgelabz.parkinglot.utility.ParkingAttendant;
import com.google.common.collect.Multimap;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Set;

public class ParkingLotSystem {

    public int parkingLotCapacity;
    public int noOfParkingLots;
    public LocalTime arrivalTime;
    public LocalTime departureTime;
    ParkingAttendant parkingAttendant = null;
    Set<ParkingLot> parkingLots = null;

    public ParkingLotSystem(int parkingLotCapacity, int noOfParkingLots) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.noOfParkingLots = noOfParkingLots;
        parkingAttendant = new ParkingAttendant(this.parkingLotCapacity, this.noOfParkingLots);
    }

    public Multimap<ParkingLot, HashMap<Slot, Vehicle>> park(Vehicle vehicle) throws ParkingLotSystemException {
        Multimap<ParkingLot, HashMap<Slot, Vehicle>> multimap = parkingAttendant.park(vehicle);
        arrivalTime = parkingAttendant.arrivalTime;
        return multimap;
    }

    public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
        parkingAttendant.unPark(vehicle);
        departureTime = parkingAttendant.departureTime;
    }

    public void register(Owner owner) {
        parkingAttendant.register(owner);
    }

    public void register(AirportPersonnel airportPersonnel) {
        parkingAttendant.register(airportPersonnel);
    }

    public boolean isVehiclePresentInLot(Vehicle vehicle) {
        return parkingAttendant.isVehiclePresentInLot(vehicle);
    }
}
