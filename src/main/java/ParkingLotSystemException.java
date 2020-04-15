public class ParkingLotSystemException extends Exception {
    public enum ExceptionType {
        PARKING_LOT_FULL;
    }
    public ExceptionType type;

    public ParkingLotSystemException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
