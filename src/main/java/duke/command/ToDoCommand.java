package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Class to run the ToDo Command.
 *
 * @author KohGuanZeh
 */
public class ToDoCommand extends Command {
    private final String toDoDescription;

    /**
     * Creates a Command that runs to add a ToDo task.
     *
     * @param input Input of ToDo information.
     * @throws CommandException Exception when there is a formatting error in the input.
     */
    public ToDoCommand(String input) throws CommandException {
        this.toDoDescription = input.trim();
        if (toDoDescription.isEmpty()) {
            throw new CommandException("Error. Unable to create ToDo task.\nFormat: " + ToDo.INPUT_TODO_FORMAT);
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException {
        String message = taskList.addTask(new ToDo(this.toDoDescription));
        storage.save(taskList.toDataString());
        return message;
    }
}
