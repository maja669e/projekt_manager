package demo.data;

import demo.model.Project;
import demo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Phuc Nguyen
 */
class TaskDatabaseTest {

    JdbcTestFixture fixture;

    @BeforeEach
    public void setup() throws SQLException {
        fixture = new JdbcTestFixture();
        fixture.setUp();
    }

    @Test
    void editTaskName() throws SQLException {
        //arrange
        Project project = fixture.getProject(1);
        fixture.addSubProject(project);

        //act
        String expTaskName = "opgave 6";

        fixture.editTask(1, "opgave 6", 25, "2021-03-03");

        //assert
        assertEquals(expTaskName, fixture.getProject(1).getSubProjects().get(1).getTasks().get(0).getTaskName());
    }

    @Test
    void editTaskTimeEstimate() throws SQLException {
        //arrange
        Project project = fixture.getProject(1);
        fixture.addSubProject(project);

        //act
        int expTimeEstimate = 25;

        fixture.editTask(1, "opgave 6", 25, "2021-03-03");

        //assert
        assertEquals(expTimeEstimate, fixture.getProject(1).getSubProjects().get(1).getTasks().get(0).getTimeEstimation());

    }

    @Test
    void editTaskDeadline() throws SQLException {
        //arrange
        Project project = fixture.getProject(1);
        fixture.addSubProject(project);

        //act
        String expDeadline = "2021-03-03";

        fixture.editTask(1, "opgave 6", 25, "2021-03-03");

        //assert
        assertEquals(expDeadline, fixture.getProject(1).getSubProjects().get(1).getTasks().get(0).getDeadLine().toString());

    }
    @Test
    void addTask() throws SQLException {
        //arrange
        fixture.addTask();

        //act
        int expTaskid = 7;
        Project project = fixture.getProject(1);

        //assert
        assertEquals(expTaskid, project.getSubProjects().get(0).getTasks().get(3).getTaskId());

    }

    @Test
    void deleteTask() throws SQLException {
        //arrange
        fixture.addTask();
        Task task = fixture.getProject(1).getSubProjects().get(0).getTasks().get(3);
        Project project = fixture.getProject(1);
        List<Task> tasks = project.getSubProjects().get(0).getTasks();

        //act
        fixture.deleteTask(7);
        boolean isInTasks = false;

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(0).equals(task)) {
                isInTasks = true;
            }
        }

        //assert
        assertFalse(isInTasks);
    }

}