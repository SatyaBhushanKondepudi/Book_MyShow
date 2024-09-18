package com.satyabhushan.bookmyshow.services.MovieTheater;

import com.satyabhushan.bookmyshow.exceptions.Movie.MovieNotFoundException;
import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
//import com.satyabhushan.bookmyshow.repositories.iMovieShowRepository;
import com.satyabhushan.bookmyshow.repositories.IShowRepository;
import com.satyabhushan.bookmyshow.repositories.ITheaterRepository;
import com.satyabhushan.bookmyshow.repositories.iMovieShowRepository;
import com.satyabhushan.bookmyshow.repositories.iMovieTheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieTheaterService {
    @Autowired
    private iMovieTheaterRepository imovieTheaterRepository;
    @Autowired
    private IShowRepository iShowRepository;
    @Autowired
    private ITheaterRepository iTheaterRepository;
//    public MovieTheaterService(iMovieTheaterRepository imovieTheaterRepository, iMovieShowRepository imovieShowRepository) {
//        this.imovieTheaterRepository = imovieTheaterRepository;
//        this.imovieShowRepository = imovieShowRepository;
//    }

    public List<String> getTheatersByMovieName(String movieName) throws MovieNotFoundException {
        List<Theater> theaterList = imovieTheaterRepository.findByMovieName(movieName);
        if(theaterList.isEmpty()){
            throw new MovieNotFoundException("No Theaters found for the movie: "+movieName);
        }

        List<String> theaterNames = new ArrayList<>();
        for(Theater theater: theaterList){
            theaterNames.add(theater.getTheaterName());
        }
        return theaterNames;
    }

    public List<Timestamp> getShowTimingsByTheaterNameAndMovieName(String theaterName, String movieName) {
        return iShowRepository.findShowTimingsByTheaterNameAndMovieName(theaterName, movieName);
    }
}
