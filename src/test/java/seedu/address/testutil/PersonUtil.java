package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return "/students {"
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
                + person.getGroup()
                + "}";
    }

    /**
     * Returns edit command string for your EditCommand format.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        return "{; ; ; }";
    }

}
