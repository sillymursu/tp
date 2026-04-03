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
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneRecord;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;

public class SetMilestoneCommandTest {

    @Test
    public void execute_sameGroupAssignment_success() throws Exception {
        AddressBook addressBook = new AddressBook();

        Person student = new PersonBuilder()
                .withName("Alice Pauline")
                .withPhone("94351253")
                .withEmail("alice@example.com")
                .withStudentId("S1")
                .withGroups("G1")
                .build();

        Assignment assignment = createAssignment("A1", "Quiz 1", "G1", "2026-04-01");

        addressBook.addPerson(student);
        addressBook.addAssignment(assignment);

        Model model = new ModelManager(addressBook, new UserPrefs());
        SetMilestoneCommand command = new SetMilestoneCommand(
                new StudentId("S1"),
                new AssignmentId("A1"),
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-30T1200H")
        );

        CommandResult result = command.execute(model);

        MilestoneRecord storedRecord = model.getMilestone(new StudentId("S1"), new AssignmentId("A1"));
        assertEquals(MilestoneStatus.COMPLETED, storedRecord.getStatus());
        assertEquals("2026-03-30T1200H", storedRecord.getCompletedAt().getValue());
        assertEquals("Set milestone for student S1 and assignment A1: COMPLETED, completedAt=2026-03-30T1200H",
                result.getFeedbackToUser());
    }

    @Test
    public void execute_differentGroupAssignment_throwsCommandException() {
        AddressBook addressBook = new AddressBook();

        Person student = new PersonBuilder()
                .withName("Alice Pauline")
                .withPhone("94351253")
                .withEmail("alice@example.com")
                .withStudentId("S1")
                .withGroups("G1")
                .build();

        Assignment assignment = createAssignment("A1", "Quiz 1", "G2", "2026-04-01");

        addressBook.addPerson(student);
        addressBook.addAssignment(assignment);

        Model model = new ModelManager(addressBook, new UserPrefs());
        SetMilestoneCommand command = new SetMilestoneCommand(
                new StudentId("S1"),
                new AssignmentId("A1"),
                MilestoneStatus.NOT_STARTED,
                new CompletedAt("")
        );

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    private Assignment createAssignment(String assignmentId, String label, String group, String dueDate) {
        return new AssignmentBuilder()
                .withAssignmentId(assignmentId)
                .withLabel(label)
                .withGroup(group)
                .withDueDate(dueDate)
                .build();
    }
}
