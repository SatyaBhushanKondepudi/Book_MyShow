package com.satyabhushan.bookmyshow.services.Movie;

import com.satyabhushan.bookmyshow.exceptions.Movie.MovieNotFoundException;
import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService implements  IMovieService {
    @Autowired
    private IMovieRepository imovieRepository;
    @Override
    public Movie addMovie(Movie movie) {
        return imovieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        Movie findMovie = imovieRepository.findById(movie.getId()).get();
        if(findMovie == null){
            throw new MovieNotFoundException("Movie not found");
        }
        findMovie.setMovieName((movie.getMovieName() != null) ? movie.getMovieName() : findMovie.getMovieName());
        findMovie.setDuration((movie.getDuration() != null) ? movie.getDuration() : findMovie.getDuration());
        findMovie.setRating((movie.getRating() != 0) ? movie.getRating() : findMovie.getRating());
        findMovie.setReleaseDate((movie.getReleaseDate() != null) ? movie.getReleaseDate() : findMovie.getReleaseDate());
        findMovie.setLanguage((movie.getLanguage() != null) ? movie.getLanguage() : findMovie.getLanguage());
        findMovie.setGenre((movie.getGenre() != null) ? movie.getGenre() : findMovie.getGenre());
        imovieRepository.save(findMovie);
        return findMovie;
    }

    @Override
    public Movie deleteMovie(Movie movie) throws MovieNotFoundException {
        Movie findMovie = imovieRepository.findById(movie.getId()).get();
        if(findMovie == null){
            throw new MovieNotFoundException("Movie not found");
        }
        imovieRepository.delete(findMovie);
        return findMovie;
    }
}
