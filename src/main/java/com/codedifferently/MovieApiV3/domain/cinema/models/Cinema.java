package com.codedifferently.MovieApiV3.domain.cinema.models;

import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cinema implements Comparable<Cinema> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Hall.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "cinemaId", referencedColumnName = "id")
    private Set<Hall> halls;


    public Cinema() {
    }

    public Cinema(String name) {
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

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }

    @Override
    public int compareTo(Cinema o) {
        return name.compareTo(o.getName());
    }
}
