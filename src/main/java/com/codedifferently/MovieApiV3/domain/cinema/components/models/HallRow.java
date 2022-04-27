package com.codedifferently.MovieApiV3.domain.cinema.components.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HallRow implements Comparable<HallRow>{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(targetEntity = HallSeat.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "hallRowId", referencedColumnName = "id")
    private Set<HallSeat> seats;

    public HallRow() {
    }

    public HallRow(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HallSeat> getSeats() {
        return seats;
    }

    public void setSeats(Set<HallSeat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "HallRow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                '}';
    }

    @Override
    public int compareTo(HallRow o) {
        return name.compareTo(o.getName());
    }
}
