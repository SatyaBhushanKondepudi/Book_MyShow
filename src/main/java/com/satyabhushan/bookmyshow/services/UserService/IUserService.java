package com.satyabhushan.bookmyshow.services.UserService;

import com.satyabhushan.bookmyshow.dtos.SignUp.SignUpRequestDto;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidCredentialsException;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidInputException;
import com.satyabhushan.bookmyshow.exceptions.User.UserAlreadyExistsException;
import com.satyabhushan.bookmyshow.exceptions.User.UserNotFoundException;
import com.satyabhushan.bookmyshow.models.Users.User;

public interface IUserService<T> {
    public T signUp(T user) throws UserAlreadyExistsException , InvalidInputException;

    T signIn(String email, String password) throws InvalidCredentialsException;

    T editUser(String email, SignUpRequestDto editUserRequestDto) throws UserNotFoundException, InvalidInputException;

    T getUser(String email) throws UserNotFoundException;
}
