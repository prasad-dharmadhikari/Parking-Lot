public class ParkingLotSystem {

    private Object vehicle;

    public boolean park(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle != null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicle = vehicle;
        return true;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle.equals(this.vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
