package dev.ritobina.BookMyShow.exceptions;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
