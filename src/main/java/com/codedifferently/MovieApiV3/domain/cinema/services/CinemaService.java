package com.codedifferently.MovieApiV3.domain.cinema.services;

import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.CinemaNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.cinema.models.Cinema;

public interface CinemaService{

    Cinema create(Cinema cinema);
    Cinema findById(Long id) throws CinemaNotFoundException;
    Cinema update(Cinema cinema) throws CinemaNotFoundException;
    void delete(Long id) throws CinemaNotFoundException;

}
