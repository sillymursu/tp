package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentBook;
import seedu.address.model.milestone.MilestoneStore;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final MilestoneStore milestoneStore;

    // NEW: assignments
    private final AssignmentBook assignmentBook;

    {
        persons = new UniquePersonList();
        milestoneStore = new MilestoneStore();
        assignmentBook = new AssignmentBook();
    }

    public AddressBook() {}

    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // ==================== overwrite operations ====================

    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setMilestoneStore(MilestoneStore milestoneStore) {
        requireNonNull(milestoneStore);
        this.milestoneStore.setMilestones(milestoneStore);
    }

    // NEW
    public void setAssignments(List<Assignment> assignments) {
        this.assignmentBook.setAssignments(assignments);
    }

    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setMilestoneStore(newData.getMilestoneStore());
        setAssignments(newData.getAssignmentList());
    }

    // ==================== person operations ====================

    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    public void removePerson(Person key) {
        persons.remove(key);
    }

    // ==================== assignment operations ====================

    public AssignmentBook getAssignmentBook() {
        return assignmentBook;
    }

    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignmentBook.hasAssignment(assignment);
    }

    public void addAssignment(Assignment assignment) {
        requireNonNull(assignment);
        assignmentBook.addAssignment(assignment);
    }

    // ==================== util ====================


    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public MilestoneStore getMilestoneStore() {
        return milestoneStore;
    }

    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return assignmentBook.getAssignmentList();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("milestoneStore", milestoneStore)
                .add("assignments", assignmentBook.getAssignmentList().size())
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && milestoneStore.equals(otherAddressBook.milestoneStore)
                && assignmentBook.getAssignmentList().equals(otherAddressBook.assignmentBook.getAssignmentList());
    }

    @Override
    public int hashCode() {
        return 31 * persons.hashCode()
                + 17 * milestoneStore.hashCode()
                + assignmentBook.getAssignmentList().hashCode();
    }
}