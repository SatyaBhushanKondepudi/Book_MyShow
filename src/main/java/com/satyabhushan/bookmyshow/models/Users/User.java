package com.satyabhushan.bookmyshow.models.Users;

import com.satyabhushan.bookmyshow.models.Base;
import com.satyabhushan.bookmyshow.models.ENUMS.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Entity
public class User extends Base {

    @Column(nullable = false)
    private String name;

    @Column(unique = true , nullable = false)
    private String email;

    private String userName;

    private String password ;

    @Column(unique = true , nullable = false)
    private String phone;

    private String address;

    private String city;

    private String state;

    private String country;

    private String zip;

    @Enumerated(EnumType.STRING)
    private UserType role;
}
