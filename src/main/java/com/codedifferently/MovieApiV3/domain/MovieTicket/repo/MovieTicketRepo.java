package com.codedifferently.MovieApiV3.domain.MovieTicket.repo;

import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieTicketRepo extends CrudRepository <MovieTicket, Long> {

}
