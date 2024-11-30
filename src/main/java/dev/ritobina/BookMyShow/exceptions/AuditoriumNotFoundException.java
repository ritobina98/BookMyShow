package dev.ritobina.BookMyShow.exceptions;

public class AuditoriumNotFoundException extends RuntimeException{
    public AuditoriumNotFoundException() {
    }

    public AuditoriumNotFoundException(String message) {
        super(message);
    }
}
