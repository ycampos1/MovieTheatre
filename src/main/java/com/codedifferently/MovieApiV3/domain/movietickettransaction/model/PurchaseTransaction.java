package com.codedifferently.MovieApiV3.domain.movietickettransaction.model;

import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import com.codedifferently.MovieApiV3.domain.customer.models.Customer;

import javax.persistence.*;

@Entity
public class PurchaseTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade =  CascadeType.DETACH)
    private MovieTicket movieTicket;

    @ManyToOne(cascade =  CascadeType.ALL)
    private Customer customer;


    public PurchaseTransaction(MovieTicket movieTicket, Customer customer) {
        this.movieTicket = movieTicket;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieTicket getMovieTicket() {
        return movieTicket;
    }

    public void setMovieTicket(MovieTicket movieTicket) {
        this.movieTicket = movieTicket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "PurchaseTransaction{" +
                "id=" + id +
                ", movieTicket=" + movieTicket +
                ", member=" + customer +
                '}';
    }
}
