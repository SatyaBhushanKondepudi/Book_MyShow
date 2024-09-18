package com.satyabhushan.bookmyshow.repositories;

import com.satyabhushan.bookmyshow.models.Show.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface iMovieShowRepository extends JpaRepository<Movie, Integer> {
}
