package com.codedifferently.MovieApiV3.domain.cinema.components;

import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.Hall;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.models.HallSeatRequest;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.repos.HallRepo;
import com.codedifferently.MovieApiV3.domain.cinema.components.hall.services.HallService;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class HallSeatRequestTest {

    @MockBean
    private HallRepo hallRepo;

    @Autowired
    private HallService hallService;

    private HallSeatRequest input;
    private HallSeatRequest output;
    private Hall hallInput;


    @BeforeEach
    public void setUp() {

        input = new HallSeatRequest(new Hall(1, LocalTime.now()),"B", "2");
        output =new HallSeatRequest(new Hall(1, LocalTime.now()),"B", "2");
        hallInput = new Hall(1, LocalTime.now());
    }


    @Test
    @DisplayName("Seat Reserved -Success")
    public void SeatReserverdTest(){
        BDDMockito.doReturn(output).when(hallRepo).save(ArgumentMatchers.any());



    }


    @Test
    @DisplayName("Seat Already Taken - Fail")
    public void SeatTakenTest(){

    }
}
