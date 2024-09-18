package com.satyabhushan.bookmyshow.exceptions.User;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
