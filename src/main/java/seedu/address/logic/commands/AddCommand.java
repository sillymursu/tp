package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. \n"
            + "Format: add /students {<name>; <phone number>; <email>; <group>}\n"
            + "Example: add /students {John; 9878 0020; johnnyjohn@gmail.com; Sec3A}";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        StudentId newId = model.getNextStudentId();
        Person personWithId = new Person(
                newId,
                toAdd.getName(),
                toAdd.getPhone(),
                toAdd.getEmail(),
                toAdd.getGroups()
        );

        model.addPerson(personWithId);
        for (Group g : toAdd.getGroups()) {
            model.addGroup(g);
            model.addStudentToGroup(g, newId);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personWithId)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
