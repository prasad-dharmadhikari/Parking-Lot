public class ParkingLotSystem {

    private Object vehicle;

    public void park(Object vehicle) throws ParkingLotSystemException {
        if (isVehicleParked())
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicle = vehicle;
    }

    public void unPark(Object vehicle) throws ParkingLotSystemException {
        if ((!isVehicleParked()))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED, "VEHICLE IS ALREADY UNPARKED");
        if (!(vehicle.equals(this.vehicle)))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.WRONG_VEHICLE, "WRONG VEHICLE DETAILS PROVIDED");
        this.vehicle = null;
    }

    public boolean isVehicleParked() {
        if (this.vehicle != null)
            return true;
        return false;
    }
}
