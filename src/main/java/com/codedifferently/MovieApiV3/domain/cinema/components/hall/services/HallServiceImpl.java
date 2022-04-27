package com.codedifferently.MovieApiV3.domain.cinema.components.hall.services;

import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.SeatNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.repos.HallRepo;
import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.HallNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallRow;
import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallSeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class HallServiceImpl implements HallService {
    private static final Logger logger = LoggerFactory.getLogger(HallServiceImpl.class);

    private static final Integer Max_Number_Of_Rows = 5;
    private HallRepo hallRepo;

    @Autowired
    public HallServiceImpl(HallRepo hallRepo) {
        this.hallRepo = hallRepo;
    }


    @Override
    public Hall create(Hall hall) {
        Integer roomNumber = hall.getRoomNumber();
        hall.setRows(generateRows(roomNumber));
        return hallRepo.save(hall);
    }

    @Override
    public Hall findById(Long id) throws HallNotFoundException {
        Optional<Hall> hallOptional = hallRepo.findById(id);
        if (hallOptional.isEmpty())
            throw new HallNotFoundException ("Hall Not Found");
        return hallOptional.get();
    }

 //   @Override
 //   public Hall findByMovie(Movie movie) {
 //       return null;
 //   }

    @Override
    public Hall updateHall(Hall hall) throws HallNotFoundException {
        Long id = hall.getId();
        Optional<Hall> hallExistExistOption = hallRepo.findById(id);
        if (hallExistExistOption.isEmpty())
            throw new HallNotFoundException("No hall exists with that id");
          return hallRepo.save(hall);

    }

    @Override
    public void deleteHall(Long id) throws HallNotFoundException {
        Optional<Hall> hallExistOption = hallRepo.findById(id);
        if(hallExistOption.isEmpty())
            throw new HallNotFoundException("No hall by ID");
        Hall hall = hallExistOption.get();
        hallRepo.delete(hall);

    }

    @Override
    public Boolean checkStatusOfSeat(HallSeatRequest seatRequest) throws SeatNotFoundException {
        Hall hall = seatRequest.getHall();
        String rowName = seatRequest.getRowRequest();
        Set <HallRow> filteredRows = hall.getRows().stream()
                .filter(hallRow -> rowName.equals(seatRequest.getRowRequest()))
                .collect(Collectors.toSet());
        if (filteredRows.isEmpty())
            throw new SeatNotFoundException(" Seat not found");
        HallRow row = filteredRows.iterator().next();
        Set<HallSeat> filteredSeats = row.getSeats().stream()
                .filter(seat -> seat.getSeatLocation().equals(seatRequest.getSeatRequest()))
                .collect(Collectors.toSet());
        if (filteredSeats.isEmpty())
            throw new SeatNotFoundException("Seat not found");
        HallSeat seat = filteredSeats.iterator().next();

        return seat.getReserved();
    }

    @Override
    public void reserveSeat(HallSeatRequest seatRequest) throws SeatNotFoundException {
        Hall hall = seatRequest.getHall();
        String rowName = seatRequest.getRowRequest();
        Set <HallRow> filteredRows = hall.getRows().stream()
                .filter(hallRow -> rowName.equals(seatRequest.getRowRequest()))
                .collect(Collectors.toSet());
        if (filteredRows.isEmpty())
            throw new SeatNotFoundException(" Seat not found");
        HallRow row = filteredRows.iterator().next();
        Set<HallSeat> filteredSeats = row.getSeats().stream()
                .filter(seat -> seat.getSeatLocation().equals(seatRequest.getSeatRequest()))
                .collect(Collectors.toSet());
        if (filteredSeats.isEmpty())
            throw new SeatNotFoundException("Seat not found");
        HallSeat seat = filteredSeats.iterator().next();
        if(seat.getReserved())
            throw new SeatNotFoundException("Seat Reserved");
        seat.setReserved(true);
        hallRepo.save(hall);
    }


    private Set <HallRow> generateRows(Integer roomNumber) {
    Set <HallRow> hallRows = new LinkedHashSet<>();
    for (int i=1; i<=Max_Number_Of_Rows;i++){
    String rowLocation = roomNumber + "_" + i;
    HallRow row = new HallRow(rowLocation);
    logger.debug("Created row %s", rowLocation);
    hallRows.add(row);
    }
    return hallRows;
    }

}
