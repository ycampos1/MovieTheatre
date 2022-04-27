package com.codedifferently.MovieApiV3.domain.cinema.components.hall.repos;

import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.movie.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HallRepo extends CrudRepository<Hall, Long> {
  //  Optional<Hall> findByMovie(Movie movie);
}
