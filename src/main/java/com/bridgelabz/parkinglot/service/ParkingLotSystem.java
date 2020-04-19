package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.Observer.Observer;
import com.bridgelabz.parkinglot.Observer.Subject;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.bridgelabz.parkinglot.utility.ParkingAttendant;

import java.util.*;

public class ParkingLotSystem implements Subject {

    public int parkingLotCapacity;
    public String vehicleName;
    public HashMap<Integer, String> parkingLot;
    private List<Observer> observers = new ArrayList<Observer>();
    ParkingAttendant parkingAttendant = new ParkingAttendant(5);

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.parkingLot = new HashMap<>();
        for (int itr = 1; itr <= parkingLotCapacity; itr++) {
            parkingLot.put(itr, null);
        }
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
            observer.sendParkingStatus(parkingLot);
        }
    }


    public void park(String vehicle) throws ParkingLotSystemException {
        this.vehicleName = vehicle;
        parkingLot = parkingAttendant.park(vehicleName, parkingLot);
        this.notifyObservers();
    }

    public boolean unPark(String vehicle) throws ParkingLotSystemException {
        if (!isVehiclePresentInLot(vehicle))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        Iterator<String> parkingLotIterator = getParkingLotIterator(parkingLot);
        this.vehicleName = vehicle;
        while (parkingLotIterator.hasNext()) {
            if (parkingLotIterator.next().equals(vehicle)) {
                parkingLotIterator.remove();
                return true;
            }
        }
        this.notifyObservers();
        return true;
    }

    private boolean isVehiclePresentInLot(String vehicle) {
        Iterator<String> parkingLotIterator = getParkingLotIterator(parkingLot);
        while (parkingLotIterator.hasNext()) {
            if (Objects.equals(parkingLotIterator.next(), vehicle))
                return true;
        }
        return false;
    }

    private Iterator<String> getParkingLotIterator(HashMap<Integer, String> parkingLot) {
        return parkingLot.values().iterator();
    }

    public boolean isVehicleParked() {
        return parkingLot.containsValue(vehicleName);
    }

    public static boolean isParkingLotFull(HashMap<Integer, String> parkingLot) {
        for (int i = 1; i <= parkingLot.size(); i++) {
            if (parkingLot.get(i) == null)
                return false;
        }
        return true;
    }
}
