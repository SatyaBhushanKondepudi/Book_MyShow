package com.satyabhushan.bookmyshow.dtos.MovieTheaterDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrowseMovieResponseDto {
    /*
    Should return the list of Theaters along with Show Timings , ShowDates and Ticket Prices
     */
    private List<TheaterShowTimings> theatersWithShowTimings;
}
