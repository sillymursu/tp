package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Deletes a person identified by their student id from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " /students: Deletes a student from LeTutor using student ID\n"
            + "Format:  delete /students STUDENT_ID\n"
            + "Example: delete /students S1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found: %1$s";

    private final StudentId studentId;

    public DeleteCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToDelete = null;
        for (Person person : lastShownList) {
            if (person.getStudentId().equals(studentId)) {
                personToDelete = person;
                break;
            }
        }

        if (personToDelete == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, studentId));
        }

        model.deletePerson(personToDelete);

        for (Group g : personToDelete.getGroups()) {
            model.removeStudentFromGroup(g, personToDelete.getStudentId());
            model.removeGroup(g);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return studentId.equals(otherDeleteCommand.studentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .toString();
    }
}
