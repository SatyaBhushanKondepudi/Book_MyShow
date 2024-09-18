package com.satyabhushan.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterRequestDto {
    private String email;
    private int screenNumber;
    private String theaterName;
    private String theaterLocation;
    private String theaterCity;
    private String theaterState;
    private String theaterCountry;
}
