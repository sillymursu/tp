package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.group.Group;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneRecord;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.milestone.StudentMilestoneView;
import seedu.address.model.milestone.StudentMilestones;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for {@link AddAssignmentCommand}.
 */
public class AddAssignmentCommandTest {

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null));
    }

    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAssignmentAdded modelStub = new ModelStubAcceptingAssignmentAdded();
        Assignment input = assignment("A0", "A-JUnit", "Sec3A", "2026-02-20");

        CommandResult result = new AddAssignmentCommand(input).execute(modelStub);

        Assignment expectedAdded = assignment("A1", "A-JUnit", "Sec3A", "2026-02-20");
        assertEquals(String.format(AddAssignmentCommand.MESSAGE_SUCCESS, Messages.formatA(expectedAdded)),
                result.getFeedbackToUser());
        assertEquals(java.util.List.of(expectedAdded), modelStub.assignmentsAdded);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Assignment existing = assignment("A5", "A-JUnit", "Sec3A", "2026-02-20");
        Assignment toAddDifferentIdSameFields = assignment("A0", "A-JUnit", "Sec3A", "2026-02-20");

        AddAssignmentCommand command = new AddAssignmentCommand(toAddDifferentIdSameFields);
        ModelStub modelStub = new ModelStubWithExistingAssignments(existing);

        assertThrows(CommandException.class, AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT, () ->
                command.execute(modelStub));
    }

    @Test
    public void equals() {
        Assignment a1 = assignment("A0", "A-JUnit", "Sec3A", "2026-02-20");
        Assignment a2 = assignment("A0", "A-Streams", "Sec3A", "2026-02-20");

        AddAssignmentCommand addA1 = new AddAssignmentCommand(a1);
        AddAssignmentCommand addA2 = new AddAssignmentCommand(a2);

        assertTrue(addA1.equals(addA1));
        assertTrue(addA1.equals(new AddAssignmentCommand(a1)));
        assertFalse(addA1.equals(1));
        assertFalse(addA1.equals(null));
        assertFalse(addA1.equals(addA2));
    }

    @Test
    public void toStringMethod() {
        Assignment a1 = assignment("A0", "A-JUnit", "Sec3A", "2026-02-20");
        AddAssignmentCommand command = new AddAssignmentCommand(a1);
        assertEquals("AddAssignmentCommand{toAdd=" + a1 + "}", command.toString());
    }

    private static Assignment assignment(String id, String label, String group, String dueDate) {
        return new AssignmentBuilder()
                .withAssignmentId(id)
                .withLabel(label)
                .withGroup(group)
                .withDueDate(dueDate)
                .build();
    }

    /**
     * A default model stub with all methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public boolean hasAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StudentMilestones getMilestones(StudentId studentId) {
            return new StudentMilestones();
        }

        @Override
        public void setMilestone(StudentId studentId, AssignmentId assignmentId,
                                 MilestoneStatus status, CompletedAt completedAt) {
            // no-op for tests
        }

        @Override
        public MilestoneRecord getMilestone(StudentId studentId, AssignmentId assignmentId) {
            return null;
        }

        @Override
        public void removeAllMilestonesForStudent(StudentId studentId) {
            // no-op for tests
        }

        @Override
        public void removeAllMilestonesForAssignment(AssignmentId assignmentId) {
            // no-op for tests
        }

        @Override
        public StudentMilestoneView getResolvedMilestones(StudentId studentId) {
            return new StudentMilestoneView(studentId, Collections.emptyList());
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSamePhone(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSameEmail(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StudentId getNextStudentId() {
            return new StudentId(PersonBuilder.DEFAULT_STUDENT_ID);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getFilteredAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Assignment> getAssignmentById(AssignmentId assignmentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AssignmentId getNextAssignmentId() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAssignment(Assignment target, Assignment editedAssignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudentToGroup(Group group, StudentId id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeStudentFromGroup(Group group, StudentId id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A model stub that contains pre-existing assignments.
     */
    private class ModelStubWithExistingAssignments extends ModelStub {
        private final ObservableList<Assignment> assignments;

        ModelStubWithExistingAssignments(Assignment... assignments) {
            this.assignments = FXCollections.observableArrayList(assignments);
        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            requireNonNull(assignment);
            return assignments.stream().anyMatch(assignment::isSameAssignment);
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            return assignments;
        }

        @Override
        public AssignmentId getNextAssignmentId() {
            return new AssignmentId("A999");
        }
    }

    /**
     * A model stub that always accepts the assignment being added.
     */
    private class ModelStubAcceptingAssignmentAdded extends ModelStub {
        final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAssignment(Assignment assignment) {
            requireNonNull(assignment);
            return assignmentsAdded.stream().anyMatch(assignment::isSameAssignment);
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            return FXCollections.observableArrayList(assignmentsAdded);
        }

        @Override
        public void addAssignment(Assignment assignment) {
            requireNonNull(assignment);
            assignmentsAdded.add(assignment);
        }

        @Override
        public AssignmentId getNextAssignmentId() {
            return new AssignmentId("A1");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
