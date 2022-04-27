package com.codedifferently.MovieApiV3.domain.movietickettransaction.services;

import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;
import com.codedifferently.MovieApiV3.domain.customer.models.Customer;
import com.codedifferently.MovieApiV3.domain.movietickettransaction.exceptions.PurchaseTransactionException;
import com.codedifferently.MovieApiV3.domain.movietickettransaction.model.PurchaseTransaction;

import java.time.LocalTime;

public interface PurchaseTransactionService {
    PurchaseTransaction purchaseTicketTransaction(Long customerId, Long movieTickedId, HallSeatRequest hallSeatRequest, LocalTime localTime) throws PurchaseTransactionException;

}
