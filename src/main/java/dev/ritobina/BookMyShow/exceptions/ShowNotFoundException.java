package dev.ritobina.BookMyShow.exceptions;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException() {
    }

    public ShowNotFoundException(String message) {
        super(message);
    }
}
