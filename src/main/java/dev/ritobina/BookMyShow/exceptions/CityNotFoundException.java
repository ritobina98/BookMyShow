package dev.ritobina.BookMyShow.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException() {
    }

    public CityNotFoundException(String message) {
        super(message);
    }
}
