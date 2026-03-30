package seedu.address.model.milestone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.person.StudentId;

public class MilestoneStoreTest {

    private final StudentId studentIdOne = new StudentId("S1");
    private final StudentId studentIdTwo = new StudentId("S2");
    private final AssignmentId assignmentIdOne = new AssignmentId("A1");
    private final AssignmentId assignmentIdTwo = new AssignmentId("A2");

    @Test
    public void setMilestone_andGetMilestone_success() {
        MilestoneStore milestoneStore = new MilestoneStore();
        MilestoneRecord milestoneRecord = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-24T1200H"));

        milestoneStore.setMilestone(studentIdOne, assignmentIdOne, milestoneRecord);

        Optional<MilestoneRecord> retrieved = milestoneStore.getMilestone(studentIdOne, assignmentIdOne);
        assertEquals(Optional.of(milestoneRecord), retrieved);
    }

    @Test
    public void getMilestone_noSuchEntry_returnsEmptyOptional() {
        MilestoneStore milestoneStore = new MilestoneStore();

        Optional<MilestoneRecord> retrieved = milestoneStore.getMilestone(studentIdOne, assignmentIdOne);
        assertFalse(retrieved.isPresent());
    }

    @Test
    public void removeAllForStudent_removesOnlyThatStudentMilestones() {
        MilestoneStore milestoneStore = new MilestoneStore();

        MilestoneRecord firstRecord = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));

        MilestoneRecord secondRecord = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-24T1200H"));

        milestoneStore.setMilestone(studentIdOne, assignmentIdOne, firstRecord);
        milestoneStore.setMilestone(studentIdTwo, assignmentIdTwo, secondRecord);

        milestoneStore.removeAllForStudent(studentIdOne);

        assertFalse(milestoneStore.getMilestone(studentIdOne, assignmentIdOne).isPresent());
        assertEquals(Optional.of(secondRecord), milestoneStore.getMilestone(studentIdTwo, assignmentIdTwo));
    }

    @Test
    public void removeAllForAssignment_removesThatAssignmentAcrossStudents() {
        MilestoneStore milestoneStore = new MilestoneStore();

        MilestoneRecord firstRecord = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));

        MilestoneRecord secondRecord = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));

        MilestoneRecord thirdRecord = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-24T1200H"));

        milestoneStore.setMilestone(studentIdOne, assignmentIdOne, firstRecord);
        milestoneStore.setMilestone(studentIdTwo, assignmentIdOne, secondRecord);
        milestoneStore.setMilestone(studentIdTwo, assignmentIdTwo, thirdRecord);

        milestoneStore.removeAllForAssignment(assignmentIdOne);

        assertFalse(milestoneStore.getMilestone(studentIdOne, assignmentIdOne).isPresent());
        assertFalse(milestoneStore.getMilestone(studentIdTwo, assignmentIdOne).isPresent());
        assertEquals(Optional.of(thirdRecord), milestoneStore.getMilestone(studentIdTwo, assignmentIdTwo));
    }
}