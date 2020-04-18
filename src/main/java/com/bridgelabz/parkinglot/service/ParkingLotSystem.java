package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.Observer.Observer;
import com.bridgelabz.parkinglot.Observer.Subject;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem implements Subject {

    private int parkingLotCapacity;
    private String vehicleName;
    private List<String> parkingLot;
    private List<Observer> observers = new ArrayList<Observer>();

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.parkingLot = new ArrayList<String>();
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
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.sendParkingStatus(parkingLot.size(), parkingLotCapacity);
        }
    }


    public void park(String vehicle) throws ParkingLotSystemException {
        if (parkingLot.size() >= parkingLotCapacity)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicleName = vehicle;
        parkingLot.add(vehicleName);
        this.notifyObservers();
    }

    public void unPark(String vehicle) throws ParkingLotSystemException {
        if (!(parkingLot.contains(vehicle)))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        parkingLot.remove(vehicle);
        this.notifyObservers();
    }

    public boolean isVehicleParked() {
        return parkingLot.contains(vehicleName);
    }
}
