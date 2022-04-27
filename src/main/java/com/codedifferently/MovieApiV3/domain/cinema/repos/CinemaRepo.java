package com.codedifferently.MovieApiV3.domain.cinema.repos;

import com.codedifferently.MovieApiV3.domain.cinema.models.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepo extends CrudRepository<Cinema, Long> {
}
