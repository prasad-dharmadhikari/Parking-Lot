import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    private int parkingLotCapacity;
    private String vehicleName;
    private List<String> parkingLot;

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.parkingLot = new ArrayList<String>();
    }

    public void park(String vehicle) throws ParkingLotSystemException {
        if (parkingLot.size() >= parkingLotCapacity)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicleName = vehicle;
        parkingLot.add(vehicleName);
    }

    public void unPark(String vehicle) throws ParkingLotSystemException {
        if (!(parkingLot.contains(vehicle)))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        parkingLot.remove(vehicle);
    }

    public boolean isVehicleParked() {
        if (parkingLot.contains(vehicleName))
            return true;
        return false;
    }
}
