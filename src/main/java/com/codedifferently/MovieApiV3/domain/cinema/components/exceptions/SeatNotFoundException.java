package com.codedifferently.MovieApiV3.domain.cinema.components.exceptions;

public class SeatNotFoundException extends Exception{
    public SeatNotFoundException(String msg){
        super(msg);
    }
}
