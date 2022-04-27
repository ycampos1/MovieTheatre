package com.codedifferently.MovieApiV3.domain.cinema.components.repos;

import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallRow;
import org.springframework.data.repository.CrudRepository;

public interface HallRowRepo extends CrudRepository<HallRow, Long> {
}
