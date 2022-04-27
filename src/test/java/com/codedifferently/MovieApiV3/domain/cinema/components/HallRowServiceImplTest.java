package com.codedifferently.MovieApiV3.domain.cinema.components;

import com.codedifferently.MovieApiV3.domain.cinema.components.exceptions.HallRowNotFoundException;
import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallRow;
import com.codedifferently.MovieApiV3.domain.cinema.components.repos.HallRowRepo;
import com.codedifferently.MovieApiV3.domain.cinema.components.services.HallRowService;
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

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class HallRowServiceImplTest {

    @MockBean
    private HallRowRepo hallRowRepo;

    @Autowired
    private HallRowService hallRowService;

    private HallRow input;
    private HallRow output;

    @BeforeEach
    public void setUp() {

        input = new HallRow("A");
        output = new HallRow("A");
        output.setId(1l);
    }

    @Test
    @DisplayName("Hall Row Create - success")
    public void createHallRowTest1() {


        BDDMockito.doReturn(output).when(hallRowRepo).save(ArgumentMatchers.any());
        HallRow returnedHallRow = hallRowService.create(input);
        Assertions.assertNotNull(output);
        Assertions.assertEquals(returnedHallRow.getId(), 1l);
    }


        @Test
        @DisplayName("Creation -number of rows are there")
        public void createHallRowWithSeats (){
            //test to see if the correct number of seats are in the row
            //set up a listener that watches Repo for when the save method is called, when it's called return output
            //use our service to create the row
            //Int expected is 5
            //Set the actual to returnHallRow.getSeats
            //then do assertEquals expected and actual

            BDDMockito.doReturn(output).when(hallRowRepo).save(ArgumentMatchers.any());
            HallRow returnHallRow = hallRowService.create(output);
            Integer expected =5;
            Integer actual = returnHallRow.getSeats().size();
            Assertions.assertEquals(expected,actual);

        }

    @Test
    @DisplayName("Update Hall Row Service: Update Task - Success")
    public void updateHallRowTestSuccess() throws HallRowNotFoundException {
        HallRow expectedHallRowUpdate = new HallRow("B");
        expectedHallRowUpdate.setId(1L);
        BDDMockito.doReturn(Optional.of(input)).when(hallRowRepo).findById(1L);
        BDDMockito.doReturn(expectedHallRowUpdate).when(hallRowRepo).save(ArgumentMatchers.any());
        HallRow actualTask = hallRowService.update(expectedHallRowUpdate);
        Assertions.assertEquals(expectedHallRowUpdate.toString(), actualTask.toString());
    }

    @Test
    @DisplayName("Hall Row Service: Update Task - Fail")
    public void updateHallRowTestFail() {

        HallRow input = new HallRow("C");
        input.setId(1L);
        BDDMockito.doReturn(Optional.empty()).when(hallRowRepo).findById(1L);
        Assertions.assertThrows(HallRowNotFoundException.class, () -> {
            hallRowService.update(input);
        });
    }


    @Test
    @DisplayName("Hall Row delete test - success")
    public void deleteHallRow() {
        BDDMockito.doReturn(Optional.empty()).when(hallRowRepo).findById(1l);
        Assertions.assertThrows(HallRowNotFoundException.class, () -> {
            hallRowService.delete(1l);
        });
    }

    @Test
    @DisplayName("Hall Row Service  find by Id - Success")
    public void findHallRowIdTest1() throws HallRowNotFoundException {
        BDDMockito.doReturn(Optional.of(input)).when(hallRowRepo).findById(1l);
        HallRow foundRow = hallRowService.findById(1l);
        Assertions.assertEquals(input.toString(), foundRow.toString());

    }

    @Test
    @DisplayName("Hall Row Service: Get Hall Row by Id - Fail")
    public void getHallRowByIdTestFailed() {
        BDDMockito.doReturn(Optional.empty()).when(hallRowRepo).findById(1L);
        Assertions.assertThrows(HallRowNotFoundException.class, () -> {
            hallRowService.findById(1L);
        });


    }
}
