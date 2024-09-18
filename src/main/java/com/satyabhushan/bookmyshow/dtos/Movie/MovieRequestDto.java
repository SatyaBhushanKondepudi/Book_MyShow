package com.satyabhushan.bookmyshow.dtos.Movie;

import com.satyabhushan.bookmyshow.models.ENUMS.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Getter
@Setter
public class MovieRequestDto {
    private int movieId;
    private String movieName;
    private Duration duration;
    private double rating;
    private Date releaseDate;
    private String language;
    private Genre genre;
}
