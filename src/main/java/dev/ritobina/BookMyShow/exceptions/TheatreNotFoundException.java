package dev.ritobina.BookMyShow.exceptions;

public class TheatreNotFoundException extends RuntimeException{
    public TheatreNotFoundException() {
    }

    public TheatreNotFoundException(String message) {
        super(message);
    }
}
