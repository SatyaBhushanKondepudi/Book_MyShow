package com.satyabhushan.bookmyshow.controllers;

import com.satyabhushan.bookmyshow.dtos.MovieTheaterDtos.BrowseMovieResponseDto;
import com.satyabhushan.bookmyshow.dtos.MovieTheaterDtos.TheaterShowTimings;
import com.satyabhushan.bookmyshow.exceptions.Movie.MovieNotFoundException;
import com.satyabhushan.bookmyshow.models.Show.Show;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import com.satyabhushan.bookmyshow.services.MovieTheater.MovieTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movieTheater")
public class MovieTheaterController {
    /*
    1. Browse Movie with MovieName => Return List of Theaters playing the movie
    2. Browse Movie with TheaterName => Return List of Movies playing in the Theater
    3. Show all the available seats in a Theater for a Movie on a particular date
    4. Ask for the number of seats to book
    5. Go To TicketBookingController to book the tickets
     */
    private MovieTheaterService movieTheaterService;
    public MovieTheaterController(MovieTheaterService movieTheaterService) {
        this.movieTheaterService = movieTheaterService;
    }

    @GetMapping("/browseMovie?movieName={movieName}")
    public ResponseEntity<BrowseMovieResponseDto> browseMovie(@RequestParam String movieName) throws MovieNotFoundException {
        /*
        private List<TheaterShowTimings> theaters;
        includes private String theaterName;
        includes private List<LocalDateTime> showTimings;
         */
        BrowseMovieResponseDto browseMovieResponseDto = new BrowseMovieResponseDto();
        TheaterShowTimings TheaterShowTimings = new TheaterShowTimings();

        List<String> theaterNamesList = movieTheaterService.getTheatersByMovieName(movieName);
        for(String theaterName: theaterNamesList){
            TheaterShowTimings.setTheaterName(theaterName);
            List<Timestamp> showTimings = movieTheaterService.getShowTimingsByTheaterNameAndMovieName(theaterName, movieName);
            for(Timestamp showTiming: showTimings){
                TheaterShowTimings.getShowTimings().add(showTiming);
            }
            browseMovieResponseDto.getTheatersWithShowTimings().add(TheaterShowTimings);
        }
        return new ResponseEntity<>(browseMovieResponseDto, HttpStatus.OK);
    }

//    @GetMapping("/browseTheater?theaterName={theaterName}&MovieName={movieName}")
//    public ResponseEntity<?> findAvailableShows(@RequestParam String theaterName, @RequestParam String movieName) {
//        List<Show> showList = movieTheaterService.getShowsByTheaterNameAndMovieName(theaterName, movieName);
//        return new ResponseEntity<>(showList, HttpStatus.OK);
//    }
}
