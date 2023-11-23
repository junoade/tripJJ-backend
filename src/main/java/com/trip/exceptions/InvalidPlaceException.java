package com.trip.exceptions;

public class InvalidPlaceException extends Exception{
    public InvalidPlaceException() {
        super("Invalid Place Exception Occurred");
    }

    public InvalidPlaceException(String msg) {
        super(msg);
    }
}
