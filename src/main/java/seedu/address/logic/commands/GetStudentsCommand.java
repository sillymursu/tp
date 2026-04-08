package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all students in the student book.
 */
public class GetStudentsCommand extends Command {

    public static final String MESSAGE_USAGE =
            "get /assignments: Lists all students.\n"
                    + "Example: get /students";

    public static final String MESSAGE_NO_STUDENTS = "No students found.";
    public static final String MESSAGE_SUCCESS = "Listed all students.";

    /**
     * Executes the command and returns a list of all students.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} containing the formatted list of students,
     *         or a message indicating that no students were found.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        ObservableList<Person> students = model.getFilteredPersonList();
        if (students.isEmpty()) {
            return new CommandResult(MESSAGE_NO_STUDENTS);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof GetStudentsCommand;
    }
}
