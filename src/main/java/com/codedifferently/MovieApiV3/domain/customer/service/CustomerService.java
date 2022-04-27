package com.codedifferently.MovieApiV3.domain.customer.service;

import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import com.codedifferently.MovieApiV3.domain.customer.exceptions.CustomerNotFoundException;
import com.codedifferently.MovieApiV3.domain.customer.models.Customer;
import org.springframework.stereotype.Service;

public interface CustomerService  {

    Customer create(Customer customer);
    Customer getById(Long id) throws CustomerNotFoundException;
    Customer getByFullName(String firstName, String lastName) throws CustomerNotFoundException;
    Customer update(Customer customer) throws CustomerNotFoundException;
    void delete(Long id) throws CustomerNotFoundException;
    Iterable<MovieTicket> getAllMovieTickets(Long id);

}
