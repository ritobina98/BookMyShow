package dev.ritobina.BookMyShow.exceptions;

public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException() {
    }

    public SeatNotFoundException(String message) {
        super(message);
    }
}
