package com.satyabhushan.bookmyshow.services.Movie;

import com.satyabhushan.bookmyshow.exceptions.Movie.MovieNotFoundException;
import com.satyabhushan.bookmyshow.models.Show.Movie;

public interface IMovieService {
    Movie addMovie(Movie movie);

    Movie updateMovie(Movie movie) throws MovieNotFoundException;

    Movie deleteMovie(Movie movie) throws MovieNotFoundException;
}
