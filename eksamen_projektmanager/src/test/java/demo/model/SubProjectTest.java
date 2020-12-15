package demo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



/**
 * @author Maja Bijedic
 */
class SubProjectTest {

    @Test
    void calSubProjectTotalTime() {

        //Arrange
        List<Task> tasks = new ArrayList<>();
        Task task = new Task(LocalDate.of(2020, 06, 06), 7, "Test");
        tasks.add(task);

        //Act
        int totalTime = 0;
        for (int i = 0; i < tasks.size(); i++) {
            totalTime += tasks.get(i).getTimeEstimation();
        }
        int expTotalTime = 7;
        //Assert
        assertEquals(expTotalTime, totalTime);

    }
}