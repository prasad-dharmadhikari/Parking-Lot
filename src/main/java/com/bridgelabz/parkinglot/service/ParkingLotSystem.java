package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.Observer.AirportPersonnel;
import com.bridgelabz.parkinglot.Observer.Owner;
import com.bridgelabz.parkinglot.entity.*;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.bridgelabz.parkinglot.utility.ParkingAttendant;
import com.bridgelabz.parkinglot.utility.ParkingLotSystemUtilities;

import java.time.LocalTime;
import java.util.*;

public class ParkingLotSystem {

    public int noOfSlotsPerLot;
    public LocalTime arrivalTime;
    ParkingAttendant parkingAttendant = null;
    ParkingLotSystemUtilities parkingUtilities = null;
    public HashMap<Slot, Vehicle> vehicleData = null;

    public ParkingLotSystem(int parkingLotCapacity, int noOfParkingLots) {
        this.parkingUtilities = new ParkingLotSystemUtilities(parkingLotCapacity, noOfParkingLots);
        this.noOfSlotsPerLot = parkingUtilities.getNoOfSlotsPerLot();
        this.parkingAttendant = new ParkingAttendant(parkingLotCapacity, noOfParkingLots, parkingUtilities.getNoOfSlotsPerLot());
        this.vehicleData = new HashMap<>();
    }

    public void register(Owner owner) {
        parkingAttendant.register(owner);
    }

    public void register(AirportPersonnel airportPersonnel) {
        parkingAttendant.register(airportPersonnel);
    }

    public boolean isVehiclePresentInLot(Vehicle vehicle) {
        return vehicleData.containsValue(vehicle);
    }

    public void park(Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotSystemException {
        vehicleData = parkingAttendant.park(vehicle, driverType, vehicleType);
    }

    public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
        vehicleData = parkingAttendant.unPark(vehicle);
    }

    public LocalTime getArrivalTime(Vehicle vehicle) {
        for (Slot slot : vehicleData.keySet()) {
            if (vehicleData.get(slot).equals(vehicle)) {
                arrivalTime = slot.getArrivalTime();
            }
        }
        return arrivalTime;
    }

    public int getWhiteCars() {
        ArrayList whiteCars = new ArrayList();
        for (Map.Entry<Slot, Vehicle> entry : vehicleData.entrySet()) {
            if (entry.getValue().colour.equals("White")) {
                whiteCars.add(entry);
            }
        }
        return whiteCars.size();
    }
}
