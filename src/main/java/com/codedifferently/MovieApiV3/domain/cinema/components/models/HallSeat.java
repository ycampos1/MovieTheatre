package com.codedifferently.MovieApiV3.domain.cinema.components.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HallSeat implements Comparable<HallSeat>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String seatLocation;

    private Boolean isReserved;

    public HallSeat() {
    }

    public HallSeat(String seatLocation) {
        this.seatLocation = seatLocation;
        this.isReserved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(String seatLocation) {
        this.seatLocation = seatLocation;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }




    @Override
    public String toString() {
        return "HallSeat{" +
                "id=" + id +
                ", seatLocation='" + seatLocation + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }


    @Override
    public int compareTo(HallSeat o) {
        return seatLocation.compareTo(o.getSeatLocation());
    }


}
