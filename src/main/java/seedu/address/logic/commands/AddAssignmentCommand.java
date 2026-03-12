package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

/**
 * Adds an assignment to the assignment library.
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "adda";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the assignment library. "
            + "Parameters: "
            + PREFIX_LABEL + "LABEL "
            + PREFIX_GROUP + "GROUP "
            + PREFIX_DUE_DATE + "DUE_DATE "
            + PREFIX_ORDER + "ORDER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LABEL + "A-JUnit "
            + PREFIX_GROUP + "Sec3A "
            + PREFIX_DUE_DATE + "2026-02-20 "
            + PREFIX_ORDER + "15";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT =
            "This assignment already exists in the assignment library.";

    private final Assignment toAdd;

    /**
     * Creates an AddAssignmentCommand to add the specified {@code Assignment}.
     */
    public AddAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        this.toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
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