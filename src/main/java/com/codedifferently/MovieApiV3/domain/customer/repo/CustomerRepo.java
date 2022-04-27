package com.codedifferently.MovieApiV3.domain.customer.repo;

import com.codedifferently.MovieApiV3.domain.customer.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
