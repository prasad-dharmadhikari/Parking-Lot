package com.bridgelabz.parkinglot.servicetest;

import com.bridgelabz.parkinglot.Observer.AirportPersonnel;
import com.bridgelabz.parkinglot.Observer.Owner;
import com.bridgelabz.parkinglot.entity.*;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.bridgelabz.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Map;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Owner owner = null;
    AirportPersonnel airportPersonnel = null;
    Vehicle vehicle1 = new Vehicle("Indigo CS", 4526, "TATA", "Blue");
    Vehicle vehicle2 = new Vehicle("Hexa", 4532, "TATA", "Black");
    Vehicle vehicle3 = new Vehicle("Fortuner", 3261, "TOYOTA", "Blue");
    Vehicle vehicle4 = new Vehicle("Honda city", 2365, "HONDA", "White");
    Vehicle vehicle5 = new Vehicle("Mercedes-Benz", 8745, "MERCEDES", "Blue");
    Vehicle vehicle6 = new Vehicle("Ford Figo", 6754, "FORD", "White");
    Vehicle vehicle7 = new Vehicle("Santro", 9317, "HYUNDAI", "Blue");
    Vehicle vehicle8 = new Vehicle("Audi R8", 4856, "AUDI", "Black");
    Vehicle vehicle9 = new Vehicle("Polo", 8642, "VOLKSWAGEN", "Blue");
    Vehicle vehicle10 = new Vehicle("BMW X5", 5863, "BMW", "Blue");

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(100, 4);
        vehicle = new Object();
        owner = new Owner();
        airportPersonnel = new AirportPersonnel();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.unPark(vehicle1);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
            parkingLotSystem.unPark(vehicle2);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            for (int i = 0; i <= 101; i++)
                parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
            parkingLotSystem.unPark(vehicle1);
            parkingLotSystem.unPark(vehicle1);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 100; i++)
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 99; i++)
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        for (int i = 0; i < 100; i++)
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertTrue(airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        for (int i = 0; i < 99; i++)
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertFalse(airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsFull_IfItHasSpaceAgain_OwnerShouldShowVacantSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 100; i++) {
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        }
        parkingLotSystem.unPark(vehicle1);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenVehicle_IfFoundInParkingLot_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle2, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle3, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle2));
    }

    @Test
    public void givenVehicle_IfNotFoundInParkingLot_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle2, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle3, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle4));
    }

    @Test
    public void givenAVehicle_WhenParkedAndThenUnparked_ShouldReturnArrivalTimeAndDepartureTime() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        Assert.assertEquals(parkingLotSystem.getArrivalTime(vehicle1), LocalTime.of(11, 10, 37));
    }

    @Test
    public void givenManyVehicles_WhenParkedEvenly_ShouldReturnPosition() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle2, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle3, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle4, DriverType.NORMAL, VehicleType.SMALL);
        for (Map.Entry<Slot, Vehicle> entry : parkingLotSystem.vehicleData.entrySet()) {
            if (entry.getValue().equals(vehicle1)) {
                Assert.assertEquals(1, entry.getKey().lot.lotID);
                Assert.assertEquals(1, entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle2)) {
                Assert.assertEquals(2, entry.getKey().lot.lotID);
                Assert.assertEquals(2, entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle3)) {
                Assert.assertEquals(3, entry.getKey().lot.lotID);
                Assert.assertEquals(3, entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle4)) {
                Assert.assertEquals(4, entry.getKey().lot.lotID);
                Assert.assertEquals(4, entry.getKey().slotID);
            }
        }
    }

    @Test
    public void givenAVehicle_IfDriverIsHandicapped_ShouldBeParkedInNearestPosition() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle2, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle3, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle4, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle5, DriverType.HANDICAPPED, VehicleType.SMALL);
        for (Map.Entry<Slot, Vehicle> entry : parkingLotSystem.vehicleData.entrySet()) {
            if (entry.getValue().getDriverType().equals(DriverType.HANDICAPPED)) {
                Assert.assertEquals(1, entry.getKey().lot.lotID);
                Assert.assertEquals(5, entry.getKey().slotID);
            }
        }
    }

    @Test
    public void givenALargeVehicle_WhenParked_ShouldBeParkedWithLargestFreeSpace() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle1, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle2, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle3, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle4, DriverType.NORMAL, VehicleType.SMALL);
        parkingLotSystem.park(vehicle5, DriverType.NORMAL, VehicleType.LARGE);
        for (Map.Entry<Slot, Vehicle> entry : parkingLotSystem.vehicleData.entrySet()) {
            if (entry.getValue().getDriverType().equals(VehicleType.LARGE)) {
                Assert.assertEquals(1, entry.getKey().lot.lotID);
                Assert.assertEquals(5, entry.getKey().slotID);
                Assert.assertEquals(6, entry.getKey().slotID);
            }
        }
    }
}