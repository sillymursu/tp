package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

public class GetAssignmentsCommand extends Command {

    public static final String MESSAGE_USAGE =
            "get /assignments: Lists all assignments.\n"
                    + "Example: get /assignments";

    private static final String MESSAGE_NO_ASSIGNMENTS = "No assignments found.";
    private static final String MESSAGE_LIST_HEADER = "Assignments:";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Assignment> assignments = model.getAssignmentList();
        if (assignments.isEmpty()) {
            return new CommandResult(MESSAGE_NO_ASSIGNMENTS);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_LIST_HEADER);

        for (Assignment a : assignments) {
            sb.append("\n")
                    .append(a.getAssignmentId())
                    .append(" | ")
                    .append(a.getLabel())
                    .append(" | group=")
                    .append(a.getGroup())
                    .append(" | due=")
                    .append(a.getDueDate());
        }

        return new CommandResult(sb.toString());
    }
}