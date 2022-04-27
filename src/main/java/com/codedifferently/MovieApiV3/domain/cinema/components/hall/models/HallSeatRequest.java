package com.codedifferently.MovieApiV3.domain.cinema.components.hall.models;




public class HallSeatRequest {

    private Hall hall;
    private String rowRequest;
    private String  seatRequest;


    public HallSeatRequest(Hall hall, String rowRequest, String seatRequest) {
        this.hall = hall;
        this.rowRequest = rowRequest;
        this.seatRequest = seatRequest;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getRowRequest() {
        return rowRequest;
    }

    public void setRowRequest(String rowRequest) {
        this.rowRequest = rowRequest;
    }

    public String getSeatRequest() {
        return seatRequest;
    }

    public void setSeatRequest(String seatRequest) {
        this.seatRequest = seatRequest;
    }

    @Override
    public String toString() {
        return "HallSeatRequest{" +
                "hall=" + hall +
                ", rowRequest='" + rowRequest + '\'' +
                ", seatRequest='" + seatRequest + '\'' +
                '}';
    }
}

