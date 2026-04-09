package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Group}, {@code Person}, {@code Assignment} objects to be used in tests.
 */
public class TypicalAddressBook {
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        // persons
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }

        // groups
        for (Group group : TypicalGroups.getTypicalGroups()) {
            ab.addGroup(group);
        }

        // assignments
        for (Assignment assignment : TypicalAssignments.getTypicalAssignments()) {
            ab.addAssignment(assignment);
        }

        return ab;
    }
}
