package com.satyabhushan.bookmyshow.dtos.SignIn;

import com.satyabhushan.bookmyshow.dtos.ResponseStatus;
import com.satyabhushan.bookmyshow.models.Users.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDto {
    private User user ;
    private String message;
    private ResponseStatus status ;
}
