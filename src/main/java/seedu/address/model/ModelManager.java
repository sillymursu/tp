package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneRecord;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.milestone.StudentId;
import seedu.address.model.milestone.StudentMilestones;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Feature 2: Milestones ======================================================================

    @Override
    public StudentMilestones getMilestones(StudentId studentId) {
        requireNonNull(studentId);
        return addressBook.getMilestoneStore().getStudentMilestones(studentId);
    }

    @Override
    public void setMilestone(StudentId studentId, AssignmentId assignmentId,
                             MilestoneStatus status, CompletedAt completedAt) {
        requireAllNonNull(studentId, assignmentId, status, completedAt);
        MilestoneRecord milestoneRecord = new MilestoneRecord(status, completedAt);
        addressBook.getMilestoneStore().setMilestone(studentId, assignmentId, milestoneRecord);
    }

    @Override
    public void removeAllMilestonesForStudent(StudentId studentId) {
        requireNonNull(studentId);
        addressBook.getMilestoneStore().removeAllForStudent(studentId);
    }

    @Override
    public void removeAllMilestonesForAssignment(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        addressBook.getMilestoneStore().removeAllForAssignment(assignmentId);
    }

    @Override
    public MilestoneRecord getMilestone(StudentId studentId, AssignmentId assignmentId) {
        requireAllNonNull(studentId, assignmentId);
        return addressBook.getMilestoneStore()
                .getMilestone(studentId, assignmentId)
                .orElse(null);
    }

    @Override
    public StudentId getNextStudentId() {
        int max = 0;
        for (Person p : addressBook.getPersonList()) {
            String raw = p.getStudentId().getValue(); // e.g. "S12"
            int parsed = parseStudentIdNumber(raw);
            if (parsed > max) {
                max = parsed;
            }
        }
        return new StudentId("S" + (max + 1));
    }

    private int parseStudentIdNumber(String raw) {
        if (raw == null) {
            return 0;
        }
        String s = raw.trim();
        if (s.length() < 2 || s.charAt(0) != 'S') {
            return 0;
        }
        String numberPart = s.substring(1);
        for (int i = 0; i < numberPart.length(); i++) {
            if (!Character.isDigit(numberPart.charAt(i))) {
                return 0;
            }
        }
        try {
            return Integer.parseInt(numberPart);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public AssignmentId getNextAssignmentId() {
        int max = 0;
        for (Assignment a : getAssignmentList()) {
            String raw = a.getAssignmentId().getValue(); // e.g., "A12"
            int parsed = parseAssignmentIdNumber(raw);
            if (parsed > max) {
                max = parsed;
            }
        }
        return new AssignmentId("A" + (max + 1));
    }

    private int parseAssignmentIdNumber(String raw) {
        if (raw == null) {
            return 0;
        }
        String s = raw.trim();
        if (s.length() < 2 || s.charAt(0) != 'A') {
            return 0;
        }
        String numberPart = s.substring(1);
        for (int i = 0; i < numberPart.length(); i++) {
            if (!Character.isDigit(numberPart.charAt(i))) {
                return 0;
            }
        }
        try {
            return Integer.parseInt(numberPart);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    // Feature 3: Assignment Library

    @Override
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return addressBook.hasAssignment(assignment);
    }

    @Override
    public void addAssignment(Assignment assignment) {
        requireNonNull(assignment);
        addressBook.addAssignment(assignment);
    }

    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return addressBook.getAssignmentList();
    }

    @Override
    public Optional<Assignment> getAssignmentById(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        return addressBook.getAssignmentList().stream()
                .filter(a -> a.getAssignmentId().equals(assignmentId))
                .findFirst();
    }
}
