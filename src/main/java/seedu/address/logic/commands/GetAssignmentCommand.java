package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;

/**
 * Shows the details of a single assignment identified by its assignment id.
 */
public class GetAssignmentCommand extends Command {

    public static final String MESSAGE_USAGE =
            "get /assignments <assignmentId>: Shows a single assignment.\n"
                    + "Example: get /assignments A301";

    public static final String MESSAGE_NOT_FOUND = "Assignment not found: %s";
    public static final String MESSAGE_GET_ASSIGNMENT_SUCCESS = "Assignment found: %s";

    private final AssignmentId assignmentId;

    /**
     * Creates a {@code GetAssignmentCommand} for the given assignment id.
     *
     * @param assignmentId The id of the assignment to retrieve.
     */
    public GetAssignmentCommand(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        this.assignmentId = assignmentId;
    }

    /**
     * Executes the command and returns the details of the specified assignment.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} containing the assignment details.
     * @throws CommandException If the assignment cannot be found.
     */
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

        return new CommandResult(String.format(MESSAGE_GET_ASSIGNMENT_SUCCESS, assignmentId));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GetAssignmentCommand)) {
            return false;
        }

        GetAssignmentCommand otherCommand = (GetAssignmentCommand) other;
        return assignmentId.equals(otherCommand.assignmentId);
    }
}
