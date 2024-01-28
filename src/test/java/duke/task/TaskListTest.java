package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void init() {
        this.taskList = new TaskList();

    }

    @Test
    public void markTask_existingIndex_success() {
        this.taskList.addTask(new ToDo("task 1"));
        try {
            this.taskList.markTask(1);
            assertEquals("Here are your list of tasks:\n    1.[T][X] task 1", this.taskList.listTasks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void markTask_indexOutOfBounds_exceptionThrown() {
        this.taskList.addTask(new ToDo("task 1"));
        try {
            this.taskList.markTask(10);
        } catch (Exception e) {
            assertEquals("Error. Task of index 10 cannot be found.", e.getMessage());
        }
    }

    @Test
    public void unmarkTask_existingIndex_success() {
        this.taskList.addTask(new Deadline("task 2",
                LocalDateTime.parse("28-01-2024 13:00", Task.INPUT_DATETIME_FORMAT)));
        try {
            this.taskList.markTask(1);
            this.taskList.unmarkTask(1);
            assertEquals("Here are your list of tasks:\n    "
                    + "1.[D][ ] task 2 (by: Sunday, January 28, 2024, 1:00 PM)", this.taskList.listTasks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void unmarkTask_indexOutOfBounds_exceptionThrown() {
        this.taskList.addTask(new ToDo("task 2"));
        try {
            this.taskList.unmarkTask(-1);
        } catch (Exception e) {
            assertEquals("Error. Task of index -1 cannot be found.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_existingIndex_success() {
        this.taskList.addTask(new Event("task 3",
                LocalDateTime.parse("28-01-2024 13:00", Task.INPUT_DATETIME_FORMAT),
                LocalDateTime.parse("29-01-2024 13:00", Task.INPUT_DATETIME_FORMAT)));
        try {
            this.taskList.deleteTask(1);
            assertEquals("Here are your list of tasks:", this.taskList.listTasks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        this.taskList.addTask(new ToDo("task 0"));
        try {
            this.taskList.deleteTask(0);
        } catch (Exception e) {
            assertEquals("Error. Task of index 0 cannot be found.", e.getMessage());
        }
    }
}
