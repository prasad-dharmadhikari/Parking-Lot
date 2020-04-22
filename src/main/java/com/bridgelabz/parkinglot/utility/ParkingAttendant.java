package com.bridgelabz.parkinglot.utility;

import com.bridgelabz.parkinglot.Observer.Observer;
import com.bridgelabz.parkinglot.Observer.Subject;
import com.bridgelabz.parkinglot.entity.ParkingLot;
import com.bridgelabz.parkinglot.entity.Slot;
import com.bridgelabz.parkinglot.entity.Vehicle;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.time.LocalTime;
import java.util.*;

public class ParkingAttendant implements Subject {
    public int parkingLotCapacity = 0;
    int noOfParkingLots = 0;
    public LocalTime departureTime;
    public LocalTime arrivalTime;
    private List<Observer> observers = new ArrayList<Observer>();
    public Multimap<ParkingLot, HashMap<Slot, Vehicle>> parkingLots = ArrayListMultimap.create();
    Collection<Map.Entry<ParkingLot, HashMap<Slot, Vehicle>>> entries = null;
    public HashMap<Slot, Vehicle> lot1Vehicles;
    public HashMap<Slot, Vehicle> lot2Vehicles;
    public HashMap<Slot, Vehicle> lot3Vehicles;
    ParkingLot lot1 = new ParkingLot(1);
    ParkingLot lot2 = new ParkingLot(2);
    ParkingLot lot3 = new ParkingLot(3);

    public ParkingAttendant(int parkingLotCapacity, int noOfParkingLots) {
        this.parkingLotCapacity = parkingLotCapacity * noOfParkingLots;
        this.noOfParkingLots = noOfParkingLots;
        this.arrivalTime = LocalTime.of(00,00,00);
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unRegister(Observer o) {
        observers.remove(observers.indexOf(o));
    }

    @Override
    public void notifyObservers(int currentlyOccupiedSlots) {
        for (Observer observer : observers) {
            observer.sendParkingStatus(currentlyOccupiedSlots, this.parkingLotCapacity);
        }
    }

    public Multimap<ParkingLot, HashMap<Slot, Vehicle>> park(Vehicle vehicle) throws ParkingLotSystemException {
        switch (vehicle.vehicleNumber % this.noOfParkingLots) {
            case 0:
                lot1Vehicles = parkVehiclesEvenly(lot1, vehicle);
                parkingLots.put(lot1, lot1Vehicles);
                break;
            case 1:
                lot2Vehicles = parkVehiclesEvenly(lot2, vehicle);
                parkingLots.put(lot2, lot2Vehicles);
                break;
            case 2:
                lot3Vehicles = parkVehiclesEvenly(lot3, vehicle);
                parkingLots.put(lot3, lot3Vehicles);
        }
        this.notifyObservers(parkingLots.size());
        return parkingLots;
    }

    public boolean unPark(Vehicle vehicle) throws ParkingLotSystemException {
        if (!isVehiclePresentInLot(vehicle))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_NOT_PRESENT, "VEHICLE NOT PRESENT");
        entries = parkingLots.entries();
        for (Map.Entry<ParkingLot, HashMap<Slot, Vehicle>> entry : entries) {
            Set<Slot> slots = entry.getValue().keySet();
            for (Slot slot : slots) {
                if (entry.getValue().get(slot).equals(vehicle)) {
                    slot.departureTime = LocalTime.of(12,19,56);
                    this.departureTime = slot.departureTime;
                    entry.getValue().remove(slot);
                    this.notifyObservers(entry.getValue().size());
                    return true;
                }
            }
        }
        return true;
    }

    public HashMap<Slot, Vehicle> parkVehiclesEvenly(ParkingLot parkingLot, Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLots.get(parkingLot).size() > this.parkingLotCapacity)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        int slotID = parkingLots.get(parkingLot).size();
        arrivalTime = LocalTime.of(11,10,37);
        Slot slot = new Slot(slotID++, arrivalTime);
        HashMap<Slot, Vehicle> temporaryMap = new HashMap<>();
        temporaryMap.put(slot, vehicle);
        return temporaryMap;
    }

    public boolean isVehiclePresentInLot(Vehicle vehicle) {
        entries = parkingLots.entries();
        for (Map.Entry<ParkingLot, HashMap<Slot, Vehicle>> entry : entries) {
            Set<Slot> slots = entry.getValue().keySet();
            for (Slot slot : slots) {
                if (entry.getValue().get(slot).equals(vehicle)) {
                    return true;
                }
            }
        }
        return false;
    }
}