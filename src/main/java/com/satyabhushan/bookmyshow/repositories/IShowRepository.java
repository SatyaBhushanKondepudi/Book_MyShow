package com.satyabhushan.bookmyshow.repositories;

import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.models.Show.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.List;

@Repository
public interface IShowRepository extends JpaRepository<Show, Long> {
    @Query("SELECT s.showDateTime FROM Show s WHERE s.theater.theaterName = :theaterName AND s.movie.movieName = :movieName")
    List<Timestamp> findShowTimingsByTheaterNameAndMovieName(@Param("theaterName") String theaterName, @Param("movieName") String movieName);
}
