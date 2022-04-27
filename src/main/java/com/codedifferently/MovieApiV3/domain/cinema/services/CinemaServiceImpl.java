package com.codedifferently.MovieApiV3.domain.cinema.services;

import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.CinemaNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.cinema.models.Cinema;
import com.codedifferently.MovieApiV3.domain.cinema.repos.CinemaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CinemaServiceImpl implements CinemaService {


    private static final Logger logger = LoggerFactory.getLogger(CinemaServiceImpl.class);
    private static final Integer Max_Halls_Per_Cinema= 20;
    private CinemaRepo cinemaRepo;

    @Autowired
    public CinemaServiceImpl(CinemaRepo cinemaRepo){
        this.cinemaRepo=cinemaRepo;
    }

    @Override
    public Cinema create(Cinema cinema) {
        cinema.setHalls(generateHalls());
        return cinemaRepo.save(cinema);

        

    }

    @Override
    public Cinema findById(Long id) throws CinemaNotFoundException {
        Optional<Cinema> cinemaOptional = cinemaRepo.findById(id);
        if(cinemaOptional.isEmpty())
            throw new CinemaNotFoundException("Cinema with ID Not Found");
        return cinemaOptional.get();
    }

    @Override
    public Cinema update(Cinema cinema) throws CinemaNotFoundException {

        Long id = cinema.getId();
        Optional <Cinema> cinemaExistOption = cinemaRepo.findById(id);
        if (cinemaExistOption.isEmpty())
            throw new CinemaNotFoundException("No cinema exists with that id");
        return cinemaRepo.save(cinema);
    }

    @Override
    public void delete(Long id) throws CinemaNotFoundException {
        Optional<Cinema> CinemaExistOption = cinemaRepo.findById(id);
        if (CinemaExistOption.isEmpty())
            throw new CinemaNotFoundException("No hall row by ID");
        Cinema cinema = CinemaExistOption.get();
        cinemaRepo.delete(cinema);
    }
    private Set<Hall> generateHalls(){
        Set<Hall> halls= new LinkedHashSet<>();
        for (int i=1; i<=Max_Halls_Per_Cinema;i++){
            Integer hallLocation = i;
            Hall hall = new Hall(hallLocation, LocalTime.now());
            logger.debug("Create row %s", hallLocation);
            halls.add(hall);
        }

        return halls;
    }
}
