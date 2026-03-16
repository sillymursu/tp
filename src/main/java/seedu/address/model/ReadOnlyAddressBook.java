package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.milestone.MilestoneStore;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    ObservableList<Person> getPersonList();

    MilestoneStore getMilestoneStore();

    /**
     * Returns an unmodifiable view of the assignments list.
     */
    ObservableList<Assignment> getAssignmentList();
}