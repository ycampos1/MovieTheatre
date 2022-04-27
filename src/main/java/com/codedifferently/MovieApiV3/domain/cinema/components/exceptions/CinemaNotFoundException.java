package com.codedifferently.MovieApiV3.domain.cinema.components.exceptions;

public class CinemaNotFoundException extends Exception{
    public CinemaNotFoundException(String msg){
        super(msg);
    }
}
