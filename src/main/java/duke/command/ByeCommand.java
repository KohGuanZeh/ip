package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Class to run Bye Command.
 *
 * @author KohGuanZeh
 */
public class ByeCommand extends Command {
    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException {
        return "Bye. See you again.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
