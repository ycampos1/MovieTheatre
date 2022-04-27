package com.codedifferently.MovieApiV3.domain.cinema.components;

import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.HallNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.SeatNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.repos.HallRepo;
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
public class HallServiceImplTest {


    @MockBean
    private HallRepo hallRepo;

    @Autowired
    private HallService hallService;

    private Hall input;
    private Hall output;
    private HallSeat seat01;
    private HallSeat seat02;


    @BeforeEach
    public void setUp() {

        input = new Hall(2, LocalTime.now());
        output = new Hall(2, LocalTime.now());
        Set<HallRow> rows = new TreeSet<>();
        Set<HallSeat> seats = new TreeSet<>();
        seat01 = new HallSeat("2");
        seat02 = new HallSeat("3");
        seat02.setReserved(true);
        seats.add(seat01);
        seats.add(seat02);
        HallRow hallRow = new HallRow("B");
        hallRow.setSeats(seats);
        rows.add(hallRow);
        output.setRows(rows);
        output.setId(1l);

    }

    @Test
    @DisplayName("Reserve seat 2 success- seat 2 has not been set to true")
    public void reserveSeatTest01() throws SeatNotFoundException {
        String rowName = "B";
        String seatLocation = "2";
         hallService.reserveSeat(new HallSeatRequest(output, rowName, seatLocation));
         Assertions.assertTrue(seat02.getReserved());
    }

    @Test
    @DisplayName("Reserve seat 3 - fail because it's already been reserved")
    public void reserveSeatTest02() throws SeatNotFoundException {
        String rowName = "B";
        String seatLocation = "3";
        Assertions.assertThrows(SeatNotFoundException.class, () -> {
            hallService.reserveSeat(new HallSeatRequest(output, rowName, seatLocation));
        });

    }

    @Test
    @DisplayName("Seat Does Not Exist - Fail")
    public void SeatDoesNotExistTest1() throws SeatNotFoundException {

        String rowName = "C";
        String seatLocation = "20";
        Assertions.assertThrows(SeatNotFoundException.class, () -> {
            hallService.checkStatusOfSeat(new HallSeatRequest(output, rowName,seatLocation));
        });

    }

    @Test
    @DisplayName("Seat check success")
    public void SeatCheckTest() throws SeatNotFoundException {
            String rowName = "B";
            String seatLocation = "2";
        hallService.checkStatusOfSeat(new HallSeatRequest(output, rowName, seatLocation));
        Assertions.assertFalse(seat01.getReserved());

        }





    @Test
    @DisplayName("Create Hall with ID - Succesfully")
    public void createHall() {

        BDDMockito.doReturn(output).when(hallRepo).save(ArgumentMatchers.any());
        Hall returnHall = hallService.create(input);
        Assertions.assertEquals(returnHall.getId(), 1l);

    }

    @Test
    @DisplayName("Create HallRows to then Create Hall - Succesfully")
    public void createHallWithRows() {

        BDDMockito.doReturn(output).when(hallRepo).save(ArgumentMatchers.any());
        Hall returnHall = hallService.create(output);
        Integer expected = 5;
        Integer actual = returnHall.getRows().size();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Hall Service find by Id - Success")
    public void findHallIdTest01() throws HallNotFoundException {
        BDDMockito.doReturn(Optional.of(input)).when(hallRepo).findById(1L);
        Hall foundHall = hallService.findById(1L);
        Assertions.assertEquals(input.toString(), foundHall.toString());

    }

    @Test
    @DisplayName("Hall Service: Get Hall by Id - Fail")
    public void getHallByIdTest02() {
        BDDMockito.doReturn(Optional.empty()).when(hallRepo).findById(1L);
        Assertions.assertThrows(HallNotFoundException.class, () -> {
            hallService.findById(1L);
        });
    }

    @Test
    @DisplayName("Update Hall Service: Update Task - Success")
    public void updateHallTestSuccess() throws HallNotFoundException {
        Hall expectedHallUpdate = new Hall(3, LocalTime.now());
        expectedHallUpdate.setId(1L);
        BDDMockito.doReturn(Optional.of(output)).when(hallRepo).findById(1L);
        BDDMockito.doReturn(expectedHallUpdate).when(hallRepo).save(ArgumentMatchers.any());
        Hall actualHall = hallService.updateHall(expectedHallUpdate);
        Assertions.assertEquals(expectedHallUpdate.toString(), actualHall.toString());
    }

    @Test
    @DisplayName("Hall Service: Update Task - Fail")
    public void updateHallTestFail() {

        Hall input = new Hall(4, LocalTime.now());
        input.setId(1L);
        BDDMockito.doReturn(Optional.empty()).when(hallRepo).findById(1L);
        Assertions.assertThrows(HallNotFoundException.class, () -> {
            hallService.updateHall(input);
        });
    }

    @Test
    @DisplayName("Hall delete test - success")
    public void deleteHall() {
        BDDMockito.doReturn(Optional.empty()).when(hallRepo).findById(1l);
        Assertions.assertThrows(HallNotFoundException.class, () -> {
            hallService.deleteHall(1l);
        });
    }
}



