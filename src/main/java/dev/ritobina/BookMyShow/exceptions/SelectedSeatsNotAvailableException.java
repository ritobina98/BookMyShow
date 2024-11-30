package dev.ritobina.BookMyShow.exceptions;

public class SelectedSeatsNotAvailableException extends RuntimeException{
    public SelectedSeatsNotAvailableException() {
    }

    public SelectedSeatsNotAvailableException(String message) {
        super(message);
    }
}
