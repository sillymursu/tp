package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;

/**
 * Adds an assignment to the assignment library.
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " /assignments: Adds an assignment to the assignment library.\n"
            + "Format: add /assignments {<label>; <group>; <dueDate>}\n"
            + "Example: add /assignments {A-JUnit; Sec3A; 2026-02-20}";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT =
            "This assignment already exists in the assignment library.";

    private final Assignment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Assignment}
     */
    public AddAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        this.toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Duplicate check by (label, group, dueDate) since AssignmentId is auto-generated
        boolean duplicate = model.getAssignmentList().stream().anyMatch(a ->
                a.getLabel().equals(toAdd.getLabel())
                        && a.getGroup().equals(toAdd.getGroup())
                        && a.getDueDate().equals(toAdd.getDueDate())
        );
        if (duplicate) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        AssignmentId newId = model.getNextAssignmentId();

        Assignment assignmentWithId = new Assignment(
                newId,
                toAdd.getLabel(),
                toAdd.getGroup(),
                toAdd.getDueDate()
        );

        model.addAssignment(assignmentWithId);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatA(assignmentWithId)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddAssignmentCommand)) {
            return false;
        }
        AddAssignmentCommand otherCommand = (AddAssignmentCommand) other;
        return toAdd.equals(otherCommand.toAdd);
    }

    @Override
    public String toString() {
        return "AddAssignmentCommand{toAdd=" + toAdd + "}";
    }
}
