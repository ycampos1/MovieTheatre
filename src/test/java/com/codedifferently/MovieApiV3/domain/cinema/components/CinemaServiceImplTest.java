package com.codedifferently.MovieApiV3.domain.cinema.components;
import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.CinemaNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.models.Cinema;
import com.codedifferently.MovieApiV3.domain.cinema.repos.CinemaRepo;
import com.codedifferently.MovieApiV3.domain.cinema.services.CinemaService;
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
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CinemaServiceImplTest {


    @MockBean
    private CinemaRepo cinemaRepo;

    @Autowired
    private CinemaService cinemaService;

    private Cinema input;
    private Cinema output;

    @BeforeEach
    public void setUp() {

        input = new Cinema("Regal");
        output = new Cinema("Regal");
        output.setId(1l);
    }

    @Test
    @DisplayName("Cinema created - success")
    public void createCinemaTest1(){
        BDDMockito.doReturn(output).when(cinemaRepo).save(ArgumentMatchers.any());
        Cinema returnedCinema = cinemaService.create(input);
        Assertions.assertNotNull(output);
        Assertions.assertEquals(returnedCinema.getId(), 1l);
    }

    @Test
    @DisplayName("Cinema created by created halls - success")
    public void createCinemaTest2(){
        BDDMockito.doReturn(output).when(cinemaRepo).save(ArgumentMatchers.any());
        Cinema returnCinema = cinemaService.create(output);
        Integer expected = 20;
        Integer actual = returnCinema.getHalls().size();
        Assertions.assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Cinema Service find by Id - Success")
    public void findCinemaIdTest1() throws CinemaNotFoundException {
        BDDMockito.doReturn(Optional.of(input)).when(cinemaRepo).findById(1L);
        Cinema foundCinema = cinemaService.findById(1L);
        Assertions.assertEquals(input.toString(), foundCinema.toString());

    }

    @Test
    @DisplayName("Cinema Service: Get Cinema by Id - Fail")
    public void findCinemaByIdTestFailed()  {
        BDDMockito.doReturn(Optional.empty()).when(cinemaRepo).findById(1L);
        Assertions.assertThrows(CinemaNotFoundException.class, () -> {
            cinemaService.findById(1L);
        });
    }

    @Test
    @DisplayName("Update Hall Row Service: Update Task - Success")
    public void updateCinemaTestSuccess() throws CinemaNotFoundException {
        Cinema expectedCinemaUpdate = new Cinema("Cinemark");
        expectedCinemaUpdate.setId(1l);
        BDDMockito.doReturn(Optional.of(input)).when(cinemaRepo).findById(1l);
        BDDMockito.doReturn(expectedCinemaUpdate).when(cinemaRepo).save(ArgumentMatchers.any());
        Cinema actualCinema = cinemaService.update(expectedCinemaUpdate);
        Assertions.assertEquals(expectedCinemaUpdate.toString(), actualCinema.toString());
    }

    @Test
    @DisplayName("Hall Row Service: Update Task - Fail")
    public void updateCinemaTestFail() {

        Cinema input = new Cinema("AMC");
        input.setId(1l);
        BDDMockito.doReturn(Optional.empty()).when(cinemaRepo).findById(1l);
        Assertions.assertThrows(CinemaNotFoundException.class, ()-> {
            cinemaService.update(input);
        });
    }

    @Test
    @DisplayName("Cinema  delete test - success")
    public void deleteHallRow () {
        BDDMockito.doReturn(Optional.empty()).when(cinemaRepo).findById(1l);
        Assertions.assertThrows(CinemaNotFoundException.class, ()-> {
            cinemaService.delete(1l);
        });
    }


}
