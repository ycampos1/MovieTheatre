package com.codedifferently.MovieApiV3.domain.MovieTicket.model;


import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class MovieTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String desiredSeat;
    private String desiredRow;
    private LocalTime showTime;
    private Integer hallRoomNumber;





    public MovieTicket( ){

    }

//    public MovieTicket(Integer hallRoomNumber, String desiredSeat,String desiredRow, LocalTime localTime) {
//        this.desiredSeat = desiredSeat;
//        this.localTime = localTime;
//        this.hallRoomNumber = hallRoomNumber;
//        this.desiredRow = desiredRow;
//    }

    public MovieTicket(HallSeatRequest hallSeatRequest, LocalTime showTime){
        this.desiredSeat=hallSeatRequest.getSeatRequest();
        this.hallRoomNumber= hallSeatRequest.getHall().getRoomNumber();
        this.desiredRow = hallSeatRequest.getRowRequest();
        this.showTime=showTime;

    }




    public String getDesiredSeat() {
        return desiredSeat;
    }

    public void setDesiredSeat(String desiredSeat) {
        this.desiredSeat = desiredSeat;
    }

    public LocalTime getLocalTime() {
        return showTime;
    }

    public void setLocalTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesiredRow() {
        return desiredRow;
    }

    public void setDesiredRow(String desiredRow) {
        this.desiredRow = desiredRow;
    }

    public Integer getHallRoomNumber() {
        return hallRoomNumber;
    }

    public void setHallRoomNumber(Integer hallRoomNumber) {
        this.hallRoomNumber = hallRoomNumber;
    }

    @Override
    public String toString() {
        return "MovieTicket{" +
                "id=" + id +
                ", desiredSeat='" + desiredSeat + '\'' +
                ", desiredRow='" + desiredRow + '\'' +
                ", localTime=" + showTime +
                ", hallRoomNumber=" + hallRoomNumber +
                '}';
    }
}

