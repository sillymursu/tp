package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneRecord;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.milestone.StudentId;
import seedu.address.model.milestone.StudentMilestones;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // =========================== Feature 2: Milestones ===========================

    /**
     * Returns the milestone records for the given student.
     */
    StudentMilestones getMilestones(StudentId studentId);

    /**
     * Sets one milestone record for the given student-assignment pair.
     */
    void setMilestone(StudentId studentId, AssignmentId assignmentId,
                      MilestoneStatus status, CompletedAt completedAt);

    /**
     * Removes all milestone records for the given student.
     */
    void removeAllMilestonesForStudent(StudentId studentId);

    /**
     * Removes all milestone records tied to the given assignment.
     */
    void removeAllMilestonesForAssignment(AssignmentId assignmentId);

    /**
     * Returns the milestone record for the given student-assignment pair, or null if absent.
     */
    MilestoneRecord getMilestone(StudentId studentId, AssignmentId assignmentId);

    /**
     * Generates the next available StudentId in the form S1, S2, S3... based on existing persons.
     */
    StudentId getNextStudentId();

    boolean hasAssignment(Assignment assignment);

    void addAssignment(Assignment assignment);

    ObservableList<Assignment> getAssignmentList();

    Optional<Assignment> getAssignmentById(AssignmentId assignmentId);

    AssignmentId getNextAssignmentId();
}
