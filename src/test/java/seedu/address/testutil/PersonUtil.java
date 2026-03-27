package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        String curr = "add /students {"
                    + person.getName().fullName + "; "
                    + person.getPhone().value + "; "
                    + person.getEmail().value + "; ";
        if (person.getGroups().isEmpty()) {
            return curr + "}";
        }
        for (Group g : person.getGroups()) {
            curr += g.getGroupName() + ", ";
        }
        return curr.substring(0, curr.length() - 2) + "}";
    }

    /**
     * Returns the person details in JSON format for your parser.
     */
    public static String getPersonDetails(Person person) {
        String curr = "{"
                + person.getName().fullName + "; "
                + person.getPhone().value + "; "
                + person.getEmail().value + "; ";
        if (person.getGroups().isEmpty()) {
            return curr + "}";
        }
        for (Group g : person.getGroups()) {
            curr += g.getGroupName() + ", ";
        }
        return curr.substring(0, curr.length() - 2) + "}";
    }

    /**
     * Returns edit command string for your EditCommand format.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        return "{; ; ; }";
    }
}
