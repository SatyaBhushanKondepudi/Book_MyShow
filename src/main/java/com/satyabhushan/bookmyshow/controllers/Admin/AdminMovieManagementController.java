package com.satyabhushan.bookmyshow.controllers.Admin;

import com.satyabhushan.bookmyshow.dtos.Movie.MovieRequestDto;
import com.satyabhushan.bookmyshow.exceptions.Movie.MovieNotFoundException;
import com.satyabhushan.bookmyshow.models.ENUMS.UserType;
import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.services.Movie.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/movie")
public class AdminMovieManagementController {
    /*
    1. Add Movie
    2. Update Movie
    3. Delete Movie
     */
    @Autowired
    private IMovieService iMovieService;

    @PostMapping("/addMovie/{userType}")
    public ResponseEntity<?> addMovie(@RequestBody MovieRequestDto movieRequestDto , @PathVariable UserType userType){
        if(userType != UserType.ADMIN){
            return new ResponseEntity<>("You are not authorized to perform this action", HttpStatus.UNAUTHORIZED);
        }
        else{
            Movie movie = buildMovieObject(movieRequestDto);
            Movie savedMovie = iMovieService.addMovie(movie);
            return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
        }
    }

    @PutMapping("/updateMovie")
    public ResponseEntity<?> updateMovie(@RequestBody MovieRequestDto movieRequestDto , UserType userType) throws MovieNotFoundException {
        if(userType != UserType.ADMIN){
            return new ResponseEntity<>("You are not authorized to perform this action", HttpStatus.UNAUTHORIZED);
        }
        else {
            Movie movie = iMovieService.updateMovie(buildMovieObject(movieRequestDto));
            return new ResponseEntity<>(movie.getMovieName() + " updated successfully", HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteMovie")
    public ResponseEntity<?> deleteMovie(@RequestBody MovieRequestDto movieRequestDto , UserType userType) throws MovieNotFoundException {
        if(userType != UserType.ADMIN){
            return new ResponseEntity<>("You are not authorized to perform this action", HttpStatus.UNAUTHORIZED);
        }
        else {
            iMovieService.deleteMovie(buildMovieObject(movieRequestDto));
            return new ResponseEntity<>(movieRequestDto.getMovieName() + " deleted successfully", HttpStatus.OK);
        }
    }

    private Movie buildMovieObject(MovieRequestDto movieRequestDto) {
        Movie movie = Movie.builder()
                .movieName(movieRequestDto.getMovieName())
                .duration(movieRequestDto.getDuration())
                .rating(movieRequestDto.getRating())
                .releaseDate(movieRequestDto.getReleaseDate())
                .language(movieRequestDto.getLanguage())
                .genre(movieRequestDto.getGenre())
                .build();
        return movie;
    }
}
