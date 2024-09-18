package com.satyabhushan.bookmyshow.dtos.SignUp;

import com.satyabhushan.bookmyshow.dtos.ResponseStatus;
import com.satyabhushan.bookmyshow.models.Users.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private User user ;
    private ResponseStatus status ;
    private String message ;

}
