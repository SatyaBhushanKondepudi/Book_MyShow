package com.satyabhushan.bookmyshow.exceptions.User;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
