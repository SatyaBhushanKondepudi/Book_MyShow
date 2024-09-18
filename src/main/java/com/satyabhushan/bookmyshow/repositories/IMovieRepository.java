package com.satyabhushan.bookmyshow.repositories;

import com.satyabhushan.bookmyshow.models.Show.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
}
