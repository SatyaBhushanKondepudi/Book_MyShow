package com.satyabhushan.bookmyshow.exceptions.User;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(String message) {
        super("Invalid credentials Entered.");
    }
}
