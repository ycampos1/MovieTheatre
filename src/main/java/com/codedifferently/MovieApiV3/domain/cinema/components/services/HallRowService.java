package com.codedifferently.MovieApiV3.domain.cinema.components.services;

import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.HallRowNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallRow;

public interface HallRowService {
    HallRow create(HallRow hallRow);
    HallRow findById(Long id) throws HallRowNotFoundException;
    HallRow update(HallRow hallRow) throws HallRowNotFoundException;
    void delete(Long id) throws HallRowNotFoundException;
}
