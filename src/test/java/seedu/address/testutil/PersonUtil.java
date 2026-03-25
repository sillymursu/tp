package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return "add /students {"
                + person.getName().fullName + "; "
                + person.getPhone().value + "; "
                + person.getEmail().value + "; "
                + person.getGroup()
                + "}";
    }

    /**
     * Returns the person details in JSON format for your parser.
     */
    public static String getPersonDetails(Person person) {
        return "{"
                + person.getName().fullName + "; "
                + person.getPhone().value + "; "
                + person.getEmail().value + "; "
                + person.getGroup().getGroupName()
                + "}";
    }

    /**
     * Returns edit command string for your EditCommand format.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        return "{; ; ; }";
    }

}
