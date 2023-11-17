package com.trip.exceptions;

public class DuplicatedException extends Exception{
    public DuplicatedException() {
        super("DuplicatedException Occurred");
    }

    public DuplicatedException(String msg) {
        super(msg);
    }
}
