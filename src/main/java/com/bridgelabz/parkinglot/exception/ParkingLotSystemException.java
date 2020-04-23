package com.bridgelabz.parkinglot.exception;

public class ParkingLotSystemException extends Exception {
    public enum ExceptionType {
        PARKING_LOT_FULL, VEHICLE_ALREADY_UNPARKED, VEHICLE_NOT_PRESENT, WRONG_VEHICLE_DETAILS, INVALID_DRIVER_TYPE;
    }
    public ExceptionType type;

    public ParkingLotSystemException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
