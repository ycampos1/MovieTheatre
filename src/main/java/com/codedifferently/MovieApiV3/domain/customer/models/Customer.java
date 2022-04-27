package com.codedifferently.MovieApiV3.domain.customer.models;

import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import com.codedifferently.MovieApiV3.domain.core.Person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Customer extends Person {
    @OneToMany(targetEntity = MovieTicket.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    Set<MovieTicket> movieTicket;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        super(firstName, lastName);

    }

}
