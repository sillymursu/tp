package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

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
import seedu.address.model.group.GroupName;
import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneRecord;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.milestone.StudentMilestoneView;
import seedu.address.model.milestone.StudentMilestones;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
        Person addedPerson = modelStub.personsAdded.get(0);
        assertEquals(validPerson.getStudentId(), addedPerson.getStudentId());
        assertEquals(validPerson.getName(), addedPerson.getName());
        assertEquals(validPerson.getPhone(), addedPerson.getPhone());
        assertEquals(validPerson.getEmail(), addedPerson.getEmail());
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHONE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePhone_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Person editedPerson = new PersonBuilder(ALICE)
                .withPhone(validPerson.getPhone().value).build();
        AddCommand addCommand = new AddCommand(editedPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHONE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Person editedPerson = new PersonBuilder(ALICE)
                .withEmail(validPerson.getEmail().value).build();
        AddCommand addCommand = new AddCommand(editedPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EMAIL, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public boolean hasAssignment(seedu.address.model.assignment.Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(seedu.address.model.assignment.Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StudentMilestones getMilestones(StudentId studentId) {
            // AddCommand does not use milestones; return empty for test compilation.
            return new StudentMilestones();
        }

        @Override
        public void setMilestone(StudentId studentId, AssignmentId assignmentId,
                                 MilestoneStatus status, CompletedAt completedAt) {
            // AddCommand does not use milestones; no-op for tests.
        }

        @Override
        public MilestoneRecord getMilestone(StudentId studentId, AssignmentId assignmentId) {
            // AddCommand does not use milestones; return empty.
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
            // AddCommand does not use resolved milestones; return empty for test compilation.
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

        @Override
        public void addAssignmentToGroup(Group group, AssignmentId id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeAssignmentFromGroup(Group group, AssignmentId id) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setFilteredPersonsAndAssignmentsByGroups(GroupName name) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean hasSamePhone(Person person) {
            requireNonNull(person);
            return this.person.hasSamePhone(person);
        }

        @Override
        public boolean hasSameEmail(Person person) {
            requireNonNull(person);
            return this.person.hasSameEmail(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public boolean hasSamePhone(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::hasSamePhone);
        }

        @Override
        public boolean hasSameEmail(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::hasSameEmail);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            if (!groupsAdded.stream().anyMatch(group::isSameGroup)) {
                groupsAdded.add(group);
            }
        }

        @Override
        public void addStudentToGroup(Group g, StudentId id) {
            for (Group group : groupsAdded) {
                try {
                    if (g.getGroupName().equals(group.getGroupName())) {
                        group.addStudent(id);
                    }
                } catch (AlreadyInGroupException e) {
                    // do nothing
                }
            }
        }
    }


}
