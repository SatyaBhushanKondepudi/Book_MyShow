package com.satyabhushan.bookmyshow.dtos.EditUser;

import com.satyabhushan.bookmyshow.dtos.ResponseStatus;
import com.satyabhushan.bookmyshow.models.Users.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserResponseDto {
    private User user ;
    private ResponseStatus status ;
    private String Message ;
}
