package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;

public class GetAssignmentCommand extends Command {

    public static final String MESSAGE_USAGE =
            "get /assignments <assignmentId>: Shows a single assignment.\n"
                    + "Example: get /assignments A301";

    private static final String MESSAGE_NOT_FOUND = "Assignment not found: %s";

    private final AssignmentId assignmentId;

    public GetAssignmentCommand(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        this.assignmentId = assignmentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Assignment> maybe = model.getAssignmentById(assignmentId);
        if (maybe.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NOT_FOUND, assignmentId));
        }

        // Update the filtered list so the UI can show only this assignment
        model.updateFilteredAssignmentList(a -> a.getAssignmentId().equals(assignmentId));

        Assignment a = maybe.get();
        String message = "Assignment details:\n"
                + "ID: " + a.getAssignmentId() + "\n"
                + "Label: " + a.getLabel() + "\n"
                + "Group: " + a.getGroup() + "\n"
                + "Due: " + a.getDueDate();

        return new CommandResult(message);
    }
}
