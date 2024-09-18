package com.satyabhushan.bookmyshow.repositories;

import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITheaterRepository extends JpaRepository<Theater , Long > {
    List<Movie> findAllMoviesByTheaterName(String theaterName);

    Optional<Theater> findByTheaterName(String theaterName);
}
