package dev.ritobina.BookMyShow.exceptions;

public class ShowSeatNotFoundException extends RuntimeException{
    public ShowSeatNotFoundException() {
    }

    public ShowSeatNotFoundException(String message) {
        super(message);
    }
}
