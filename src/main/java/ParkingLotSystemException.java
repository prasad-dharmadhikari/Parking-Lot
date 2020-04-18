public class ParkingLotSystemException extends Exception {
    public enum ExceptionType {
        PARKING_LOT_FULL, VEHICLE_ALREADY_UNPARKED, WRONG_VEHICLE;
    }
    public ExceptionType type;

    public ParkingLotSystemException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
