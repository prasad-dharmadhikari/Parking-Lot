import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(new Object());
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isVehicleUnParked = parkingLotSystem.isVehicleParked();
        Assert.assertFalse(isVehicleUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {

        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(new Object());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParkedAndTriedToParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.unPark(vehicle);
            parkingLotSystem.unPark(vehicle);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED, e.type);
        }
    }
}
