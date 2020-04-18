package com.bridgelabz.parkinglot.servicetest;

import com.bridgelabz.parkinglot.Observer.AirportPersonnel;
import com.bridgelabz.parkinglot.Observer.Owner;
import com.bridgelabz.parkinglot.exception.ParkingLotSystemException;
import com.bridgelabz.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Owner owner = null;
    AirportPersonnel airportPersonnel = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(5);
        vehicle = new Object();
        owner = new Owner();
        airportPersonnel = new AirportPersonnel();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park("Tata Indigo CS");
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.unPark("Toyota Fortuner");
        boolean isVehicleUnParked = parkingLotSystem.isVehicleParked();
        Assert.assertFalse(isVehicleUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {

        try {
            parkingLotSystem.park("Tata Indigo CS");
            parkingLotSystem.unPark("Toyota Fortuner");
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            parkingLotSystem.park("Tata Indigo CS");
            parkingLotSystem.park("Toyota Fortuner");
            parkingLotSystem.park("Maruti Swift Dzire");
            parkingLotSystem.park("Tata Hexa");
            parkingLotSystem.park("Maruti 800");
            parkingLotSystem.park("Suzuki Nexa");
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park("Tata Indigo CS");
            parkingLotSystem.unPark("Tata Indigo CS");
            parkingLotSystem.unPark("Tata Indigo CS");
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Hexa");
        parkingLotSystem.park("Maruti 800");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Hexa");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Hexa");
        parkingLotSystem.park("Maruti 800");
        Assert.assertEquals(true, airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        Assert.assertFalse(airportPersonnel.isParkingFull());
    }
}
