package com.satyabhushan.bookmyshow.dtos.MovieTheaterDtos;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.util.List;

@Getter
@Setter
public class TheaterShowTimings {
    private String theaterName;
    private List<Timestamp> showTimings;
}
