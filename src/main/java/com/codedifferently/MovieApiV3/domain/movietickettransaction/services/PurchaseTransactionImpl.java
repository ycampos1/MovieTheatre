package com.codedifferently.MovieApiV3.domain.movietickettransaction.services;

import com.codedifferently.MovieApiV3.domain.MovieTicket.exceptions.MovieTicketNotFound;
import com.codedifferently.MovieApiV3.domain.MovieTicket.exceptions.MovieTicketPurchaseException;
import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import com.codedifferently.MovieApiV3.domain.MovieTicket.services.MovieTicketService;
import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.SeatNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;
import com.codedifferently.MovieApiV3.domain.customer.exceptions.CustomerNotFoundException;
import com.codedifferently.MovieApiV3.domain.customer.models.Customer;
import com.codedifferently.MovieApiV3.domain.customer.service.CustomerService;
import com.codedifferently.MovieApiV3.domain.movietickettransaction.exceptions.PurchaseTransactionException;
import com.codedifferently.MovieApiV3.domain.movietickettransaction.model.PurchaseTransaction;
import com.codedifferently.MovieApiV3.domain.movietickettransaction.repo.PurchaseTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class PurchaseTransactionImpl implements  PurchaseTransactionService {
    private MovieTicketService movieTicketService;
    private CustomerService customerService;
    private PurchaseTransactionRepo purchaseTransactionRepo;

    @Autowired

    public PurchaseTransactionImpl(MovieTicketService movieTicketService, CustomerService customerService, PurchaseTransactionRepo purchaseTransactionRepo) {
        this.movieTicketService = movieTicketService;
        this.customerService = customerService;
        this.purchaseTransactionRepo = purchaseTransactionRepo;
    }


    @Override
    public PurchaseTransaction purchaseTicketTransaction(Long customerId, Long movieTickedId, HallSeatRequest hallSeatRequest, LocalTime localTime) throws PurchaseTransactionException {
        try {
            Customer customer = customerService.getById(customerId);
            MovieTicket movieTicket = movieTicketService.findById(movieTickedId);
            movieTicket = movieTicketService.purchaseTicket(hallSeatRequest, localTime);
            PurchaseTransaction movieTicketTransaction = new PurchaseTransaction(movieTicket, customer);
            return purchaseTransactionRepo.save(movieTicketTransaction);
        } catch (CustomerNotFoundException e) {
            throw new PurchaseTransactionException("Customer not found");
        } catch (MovieTicketNotFound e) {
            throw new PurchaseTransactionException("Movie Ticket not found");
        } catch (MovieTicketPurchaseException e) {
            throw new PurchaseTransactionException("Movie Ticket could not be purchased");
        } catch (SeatNotFoundException e) {
            throw new PurchaseTransactionException("Seat not found exception");
        }
    }
}
