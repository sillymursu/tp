package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;

public class GetStudentMilestonesCommandTest {

    @Test
    public void execute_validStudentId_success() throws Exception {
        AddressBook addressBook = new AddressBook();
        Person student = new PersonBuilder()
                .withName("Alice Pauline")
                .withPhone("94351253")
                .withEmail("alice@example.com")
                .withStudentId("S1")
                .build();

        Assignment assignment = createAssignment("A1", "Quiz 1", "G1", "2026-04-01", "1");

        addressBook.addPerson(student);
        addressBook.addAssignment(assignment);
        addressBook.getMilestoneStore().setMilestone(
                new StudentId("S1"),
                new AssignmentId("A1"),
                new seedu.address.model.milestone.MilestoneRecord(
                        MilestoneStatus.COMPLETED,
                        new CompletedAt("2026-03-20T1200H"))
        );

        Model model = new ModelManager(addressBook, new UserPrefs());
        GetStudentMilestonesCommand command = new GetStudentMilestonesCommand(new StudentId("S1"));

        CommandResult result = command.execute(model);

        String feedback = result.getFeedbackToUser();
        assertContains(feedback, "Milestones for Alice Pauline (S1)");
        assertContains(feedback, "A1");
        assertContains(feedback, "COMPLETED");
    }

    @Test
    public void execute_studentNotFound_throwsCommandException() {
        AddressBook addressBook = new AddressBook();
        Model model = new ModelManager(addressBook, new UserPrefs());

        GetStudentMilestonesCommand command = new GetStudentMilestonesCommand(new StudentId("S99"));

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_noAssignments_returnsMessage() throws Exception {
        AddressBook addressBook = new AddressBook();
        Person student = new PersonBuilder()
                .withName("Alice Pauline")
                .withPhone("94351253")
                .withEmail("alice@example.com")
                .withStudentId("S1")
                .build();

        addressBook.addPerson(student);

        Model model = new ModelManager(addressBook, new UserPrefs());
        GetStudentMilestonesCommand command = new GetStudentMilestonesCommand(new StudentId("S1"));

        CommandResult result = command.execute(model);

        assertEquals("There are no assignments in the system.", result.getFeedbackToUser());
    }

    private Assignment createAssignment(String assignmentId, String label, String group,
                                        String dueDate, String order) {
        return new Assignment(
                new AssignmentId(assignmentId),
                new Label(label),
                new Group(group),
                new DueDate(dueDate)
        );
    }

    private void assertContains(String actual, String expectedSubstring) {
        if (!actual.contains(expectedSubstring)) {
            throw new AssertionError("Expected to find substring:\n"
                    + expectedSubstring + "\ninside:\n" + actual);
        }
    }
}
