package com.codedifferently.MovieApiV3.domain.cinema.components;

import com.codedifferently.MovieApiV3.domain.MovieTicket.exceptions.MovieTicketNotFound;
import com.codedifferently.MovieApiV3.domain.MovieTicket.exceptions.MovieTicketPurchaseException;
import com.codedifferently.MovieApiV3.domain.MovieTicket.model.MovieTicket;
import com.codedifferently.MovieApiV3.domain.MovieTicket.repo.MovieTicketRepo;
import com.codedifferently.MovieApiV3.domain.MovieTicket.services.MovieTicketService;
import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.SeatNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.services.HallService;
import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallRow;
import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallSeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MovieTicketServiceTest {

    @MockBean
    HallService hallService;

    @MockBean
    MovieTicketRepo movieTicketRepo;

    @Autowired
     private MovieTicketService movieTicketService;


    private HallSeatRequest hallSeatRequest;
    private Hall input;
    private Hall output;
    private HallSeat seat01;
    private MovieTicket movieTicket;

    @BeforeEach
    public void setUp() throws MovieTicketPurchaseException, SeatNotFoundException {

        hallSeatRequest = new HallSeatRequest(new Hall(1, LocalTime.parse("01:25")), "A", "2");
//        input = new Hall(1, LocalTime.now());
//        output = new Hall(1, LocalTime.now());
//        Set<HallRow> rows = new TreeSet<>();
//        Set<HallSeat> seats = new TreeSet<>();
//        seat01 = new HallSeat("2");
//        seat01.setReserved(false);
//        seats.add(seat01);
//        HallRow hallRow = new HallRow("A");
//        hallRow.setSeats(seats);
//        rows.add(hallRow);
//        output.setRows(rows);
//        output.setId(1l);
        movieTicket = new MovieTicket(hallSeatRequest, LocalTime.parse("01:25"));
        movieTicket.setId(1l);



    }

    @Test
    @DisplayName("Purchase MovieTicket Successfully")
    public void PurchaseTicketTest01() throws MovieTicketPurchaseException, SeatNotFoundException {


        BDDMockito.doReturn(movieTicket).when(movieTicketRepo).save(ArgumentMatchers.any());
        BDDMockito.doNothing().when(hallService).reserveSeat(ArgumentMatchers.any());
        MovieTicket movieTicketPurchased =  movieTicketService.purchaseTicket(hallSeatRequest, movieTicket.getLocalTime());

        Assertions.assertEquals("MovieTicket{" +
                "id=" + 1l +
                ", desiredSeat='" + "2" + '\'' +
                ", desiredRow='" + "A" + '\'' +
                ", localTime=" + LocalTime.parse("01:25") +
                ", hallRoomNumber=" + 1 +
                '}', movieTicketPurchased.toString());
    }

    @Test
    @DisplayName("Update Movie Ticket Service: Update Task - Success")
    public void updateMovieTicketTestSuccess() throws MovieTicketNotFound {
        hallSeatRequest = new HallSeatRequest(new Hall(1, LocalTime.parse("01:25")), "B", "3");
        MovieTicket expectedMovieTicketUpdate = new MovieTicket(hallSeatRequest, LocalTime.parse("01:25"));;
        expectedMovieTicketUpdate.setId(1l);
        BDDMockito.doReturn(Optional.of(input)).when(movieTicketRepo).findById(1l);
        BDDMockito.doReturn(expectedMovieTicketUpdate).when(movieTicketRepo).save(ArgumentMatchers.any());
        MovieTicket actualMovieTicket = movieTicketService.update(expectedMovieTicketUpdate);
        Assertions.assertEquals(expectedMovieTicketUpdate.toString(), actualMovieTicket.toString());
    }

    @Test
    @DisplayName("Update Movie Ticket Service: Update Task - Fail")
    public void updateMovieTicketTestFail() {

        hallSeatRequest = new HallSeatRequest(new Hall(1, LocalTime.parse("01:25")), "D", "3");
        MovieTicket expectedMovieTicketUpdate = new MovieTicket(hallSeatRequest, LocalTime.parse("02:25"));;
        expectedMovieTicketUpdate.setId(1L);
        BDDMockito.doReturn(Optional.empty()).when(movieTicketRepo).findById(1L);
        Assertions.assertThrows(MovieTicketNotFound.class, () -> {
            movieTicketService.update(expectedMovieTicketUpdate);
        });
    }

    @Test
    @DisplayName("Movie Ticket delete test - success")
    public void deleteMovieTicketTestSuccess() {
        BDDMockito.doReturn(Optional.empty()).when(movieTicketRepo).findById(1l);
        Assertions.assertThrows(MovieTicketNotFound.class, () -> {
            movieTicketService.delete(1l);
        });
    }
}
