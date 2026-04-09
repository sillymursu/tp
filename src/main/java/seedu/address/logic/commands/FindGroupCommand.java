package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.group.GroupName;

/**
 * Finds and lists all persons and assignments in address book whose name matches the group name given.
 * Keyword matching is case sensitive.
 */
public class FindGroupCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons and assignments "
            + "that are part of the group searched and displays them "
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD + " /groups " + "Science";

    private final GroupName name;

    public FindGroupCommand(GroupName name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFilteredPersonsAndAssignmentsByGroups(name);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_AND_ASSIGNMENTS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(),
                        model.getFilteredAssignmentList().size(),
                        this.name
                ));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindGroupCommand)) {
            return false;
        }

        FindGroupCommand otherFindCommand = (FindGroupCommand) other;
        return name.equals(otherFindCommand.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Group Name", name)
                .toString();
    }
}
