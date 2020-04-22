package com.bridgelabz.parkinglot.servicetest;

import com.bridgelabz.parkinglot.Observer.AirportPersonnel;
import com.bridgelabz.parkinglot.Observer.Owner;
import com.bridgelabz.parkinglot.entity.ParkingLot;
import com.bridgelabz.parkinglot.entity.Slot;
import com.bridgelabz.parkinglot.entity.Vehicle;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.bridgelabz.parkinglot.service.ParkingLotSystem;
import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Owner owner = null;
    AirportPersonnel airportPersonnel = null;
    Vehicle vehicle1 = new Vehicle(1,"Indigo CS", 4526, "TATA", "Blue", Vehicle.Type.SMALL);
    Vehicle vehicle2 = new Vehicle(2,"Hexa", 4532, "TATA", "Black", Vehicle.Type.SMALL);
    Vehicle vehicle3 = new Vehicle(3,"Fortuner", 3261, "TOYOTA", "Blue", Vehicle.Type.SMALL);
    Vehicle vehicle4 = new Vehicle(4,"Honda city", 2365, "HONDA", "White", Vehicle.Type.SMALL);
    Vehicle vehicle5 = new Vehicle(5,"Mercedes-Benz", 8745, "MERCEDES", "Blue", Vehicle.Type.SMALL);
    Vehicle vehicle6 = new Vehicle(6,"Ford Figo", 6754, "FORD", "White", Vehicle.Type.SMALL);
    Vehicle vehicle7 = new Vehicle(7,"Santro", 9317, "HYUNDAI", "Blue", Vehicle.Type.SMALL);
    Vehicle vehicle8 = new Vehicle(8,"Audi R8", 4856, "AUDI", "Black", Vehicle.Type.SMALL);
    Vehicle vehicle9 = new Vehicle(9,"Polo", 8642, "VOLKSWAGEN", "Blue", Vehicle.Type.SMALL);
    Vehicle vehicle10 = new Vehicle(10,"BMW X5", 5863, "BMW", "Blue", Vehicle.Type.SMALL);
    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(3,3);
        vehicle = new Object();
        owner = new Owner();
        airportPersonnel = new AirportPersonnel();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle1);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle2);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
            parkingLotSystem.park(vehicle4);
            parkingLotSystem.park(vehicle5);
            parkingLotSystem.park(vehicle6);
            parkingLotSystem.park(vehicle7);
            parkingLotSystem.park(vehicle8);
            parkingLotSystem.park(vehicle9);
            parkingLotSystem.park(vehicle10);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle1);
            parkingLotSystem.unPark(vehicle1);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        parkingLotSystem.park(vehicle6);
        parkingLotSystem.park(vehicle7);
        parkingLotSystem.park(vehicle8);
        parkingLotSystem.park(vehicle9);
        parkingLotSystem.park(vehicle10);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        parkingLotSystem.park(vehicle6);
        parkingLotSystem.park(vehicle7);
        parkingLotSystem.park(vehicle8);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        parkingLotSystem.park(vehicle6);
        parkingLotSystem.park(vehicle7);
        parkingLotSystem.park(vehicle8);
        parkingLotSystem.park(vehicle9);
        parkingLotSystem.park(vehicle10);
        Assert.assertTrue(airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assert.assertFalse(airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsFull_IfItHasSpaceAgain_OwnerShouldShowVacantSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        parkingLotSystem.park(vehicle6);
        parkingLotSystem.park(vehicle7);
        parkingLotSystem.park(vehicle8);
        parkingLotSystem.park(vehicle9);
        parkingLotSystem.park(vehicle10);
        parkingLotSystem.unPark(vehicle4);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenVehicle_IfFoundInParkingLot_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle2));
    }

    @Test
    public void givenVehicle_IfNotFoundInParkingLot_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle4));
    }

    @Test
    public void givenAVehicle_WhenParkedAndThenUnparked_ShouldReturnArrivalTimeAndDepartureTime() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle1);
        System.out.println(parkingLotSystem.arrivalTime);
        System.out.println(parkingLotSystem.departureTime);
        Assert.assertEquals(parkingLotSystem.arrivalTime, LocalTime.of(11,10,37));
        Assert.assertEquals(parkingLotSystem.departureTime, LocalTime.of(12,19,56));
    }

    @Test
    public void givenManyVehicles_WhenParkedEvenly_ShouldReturnPosition() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        Multimap<ParkingLot, HashMap<Slot, Vehicle>> park = parkingLotSystem.park(vehicle3);
        Collection<Map.Entry<ParkingLot, HashMap<Slot, Vehicle>>> vehicleEntries = park.entries();
        for ( Map.Entry<ParkingLot, HashMap<Slot, Vehicle>> entry : vehicleEntries) {
            Assert.assertEquals(1,entry.getValue().size());
        }
        Assert.assertEquals(3,park.size());
    }
}