package com.satyabhushan.bookmyshow.repositories;

import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iMovieTheaterRepository extends JpaRepository<Movie, Integer> {
    List<Theater> findByMovieName(String movieName);
}
