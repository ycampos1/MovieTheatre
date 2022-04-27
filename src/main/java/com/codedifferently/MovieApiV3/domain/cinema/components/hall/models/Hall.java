package com.codedifferently.MovieApiV3.domain.cinema.components.hall.models;

import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallRow;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Hall implements Comparable<Hall> {

    @Id
    @GeneratedValue
    private Long id;

    private Integer roomNumber;
    @OneToMany(targetEntity = HallRow.class, cascade = CascadeType.ALL, fetch= FetchType.EAGER, orphanRemoval =true )
    @JoinColumn(name = "hallId", referencedColumnName = "id")
    private Set <HallRow> rows;

    private LocalTime showTime;

    public Hall() {
    }

    public Hall(Integer roomNumber, LocalTime showTime) {
        this.roomNumber = roomNumber;
        this.showTime = showTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }


    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public Set<HallRow> getRows() {
        return rows;
    }

    public void setRows(Set<HallRow> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", rows=" + rows +
                ", showTime=" + showTime +
                '}';
    }

    @Override
    public int compareTo(Hall o) {
        return roomNumber.compareTo(o.getRoomNumber());
    }
}
