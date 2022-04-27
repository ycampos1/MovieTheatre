package com.codedifferently.MovieApiV3.domain.MovieTicket.services;


import com.codedifferently.MovieApiV3.domain.MovieTicket.exceptions.MovieTicketNotCreatedException;
import com.codedifferently.MovieApiV3.domain.MovieTicket.exceptions.MovieTicketNotFound;
import com.codedifferently.MovieApiV3.domain.MovieTicket.exceptions.MovieTicketPurchaseException;
import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.SeatNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;

import java.time.LocalTime;

public interface MovieTicketService {

    MovieTicket findById(Long id) throws MovieTicketNotFound;
    MovieTicket update(MovieTicket movieTicket) throws MovieTicketNotFound;
    void delete(Long id) throws MovieTicketNotFound;
    MovieTicket purchaseTicket (HallSeatRequest hallSeatRequest, LocalTime localTime) throws MovieTicketPurchaseException, SeatNotFoundException;
}
