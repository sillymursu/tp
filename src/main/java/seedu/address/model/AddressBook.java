package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentBook;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupManager;
import seedu.address.model.milestone.MilestoneStore;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
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
    private final GroupManager groups;

    {
        persons = new UniquePersonList();
        milestoneStore = new MilestoneStore();
        assignmentBook = new AssignmentBook();
        groups = new GroupManager();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // ==================== overwrite operations ====================

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person list with {@code milestoneStores}.
     */
    public void setMilestoneStore(MilestoneStore milestoneStore) {
        requireNonNull(milestoneStore);
        this.milestoneStore.setMilestones(milestoneStore);
    }

    /**
     * Replaces the contents of the person list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignmentBook.setAssignments(assignments);
    }

    /**
     * Replaces all data in the address book with data from specified {@code ReadOnlyAddressBook}
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setMilestoneStore(newData.getMilestoneStore());
        setAssignments(newData.getAssignmentList());
    }

    // ==================== person operations ====================
    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    // ==================== assignment operations ====================

    /**
     * Returns current assignment book
     */
    public AssignmentBook getAssignmentBook() {
        return assignmentBook;
    }

    /**
     * Returns true if an assignment with the same identity as {@code assignment} exists in the address book.
     */
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignmentBook.hasAssignment(assignment);
    }

    /**
     * Adds an assignment to the address book.
     * The assignment must not already exist in the address book.
     */
    public void addAssignment(Assignment assignment) {
        requireNonNull(assignment);
        assignmentBook.addAssignment(assignment);
    }

    /**
     * Deletes an assignment from the address book.
     * The assignment must already exist in the address book.
     */
    public void deleteAssignment(Assignment assignment) {
        requireNonNull(assignment);
        assignmentBook.removeAssignment(assignment);
    }

    // ==================== groups ====================

    /**
     * Adds a group to the group manager
     * @param group group to be added
     */
    public void addGroup(Group group) {
        requireNonNull(group);
        groups.addGroup(group);
    }

    /**
     * Removes given group from group manager
     * @param group group to be removed
     */
    public void removeGroup(Group group) {
        requireNonNull(group);
        groups.removeGroup(group);
    }

    /**
     * Checks if there are duplicate groups
     * @param group group to be checked
     * @return true if exists and false otherwise
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return !groups.validateAddGroup(group);
    }

    public void addStudentToGroup(Group g, StudentId id) {
        groups.addStudentToGroup(g, id);
    }

    public void removeStudentFromGroup(Group g, StudentId id) {
        groups.removeStudentFromGroup(g, id);
    }

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
    public ArrayList<Group> getGroups() {
        return groups.getGroups();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("milestoneStore", milestoneStore)
                .add("assignments", assignmentBook.getAssignmentList().size())
                .add("groups", groups.getGroups().toArray().length)
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
                && assignmentBook.getAssignmentList().equals(otherAddressBook.assignmentBook.getAssignmentList())
                && groups.getGroups().equals(otherAddressBook.groups.getGroups());
    }

    @Override
    public int hashCode() {
        return 31 * persons.hashCode()
                + 17 * milestoneStore.hashCode()
                + assignmentBook.getAssignmentList().hashCode()
                + groups.hashCode();
    }
}
