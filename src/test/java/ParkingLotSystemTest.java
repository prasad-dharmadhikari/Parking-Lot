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
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldReturnFalse() {
        boolean isUnParked = parkingLotSystem.unPark(new Object());
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParkedAndTriedToParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.park(vehicle);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }
}
