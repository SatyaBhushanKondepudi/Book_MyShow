package com.satyabhushan.bookmyshow.dtos.SignUp;

import com.satyabhushan.bookmyshow.models.ENUMS.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password ;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private UserType role;
}
