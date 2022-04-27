package com.codedifferently.MovieApiV3.domain.actor.models;


import com.codedifferently.MovieApiV3.domain.core.Person;

import javax.persistence.Entity;

@Entity
public class Actor extends Person {

    private String alias;

    public Actor() {
    }

    public Actor(String firstName, String lastName, String alias) {
        super(firstName, lastName);
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "alias='" + alias + '\'' +
                '}';
    }
}
