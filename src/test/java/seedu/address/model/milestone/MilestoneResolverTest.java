package seedu.address.model.milestone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.AssignmentBuilder;

public class MilestoneResolverTest {

    private static final StudentId VALID_STUDENT_ID = new StudentId("S1");

    private final MilestoneResolver milestoneResolver = new MilestoneResolver();

    @Test
    public void resolveForStudent_missingMilestone_defaultsToNotStarted() {
        Assignment assignment = createAssignment("A1", "Quiz 1", "G1", "2026-04-01");
        StudentMilestones studentMilestones = new StudentMilestones();

        StudentMilestoneView result = milestoneResolver.resolveForStudent(
                VALID_STUDENT_ID,
                List.of(assignment),
                studentMilestones,
                LocalDate.of(2026, 3, 20)
        );

        assertEquals(1, result.getMilestones().size());
        assertEquals(ResolvedMilestoneStatus.NOT_STARTED, result.getMilestones().get(0).getResolvedStatus());
        assertEquals(MilestoneStatus.NOT_STARTED, result.getMilestones().get(0).getRawStatus());
        assertEquals("", result.getMilestones().get(0).getCompletedAt().getValue());
    }

    @Test
    public void resolveForStudent_completedMilestone_staysCompleted() {
        Assignment assignment = createAssignment("A1", "Quiz 1", "G1", "2026-03-01");
        StudentMilestones studentMilestones = new StudentMilestones();
        studentMilestones.set(assignment.getAssignmentId(),
                new MilestoneRecord(MilestoneStatus.COMPLETED, new CompletedAt("2026-02-28T1200H")));

        StudentMilestoneView result = milestoneResolver.resolveForStudent(
                VALID_STUDENT_ID,
                List.of(assignment),
                studentMilestones,
                LocalDate.of(2026, 3, 20)
        );

        assertEquals(ResolvedMilestoneStatus.COMPLETED, result.getMilestones().get(0).getResolvedStatus());
    }

    @Test
    public void resolveForStudent_notStartedPastDue_becomesOverdue() {
        Assignment assignment = createAssignment("A1", "Quiz 1", "G1", "2026-03-01");
        StudentMilestones studentMilestones = new StudentMilestones();
        studentMilestones.set(assignment.getAssignmentId(),
                new MilestoneRecord(MilestoneStatus.NOT_STARTED, new CompletedAt("")));

        StudentMilestoneView result = milestoneResolver.resolveForStudent(
                VALID_STUDENT_ID,
                List.of(assignment),
                studentMilestones,
                LocalDate.of(2026, 3, 20)
        );

        assertEquals(ResolvedMilestoneStatus.OVERDUE, result.getMilestones().get(0).getResolvedStatus());
    }

    @Test
    public void resolveForStudent_assignmentOrder_preserved() {
        Assignment firstAssignment = createAssignment("A1", "Quiz 1", "G1", "2026-04-01");
        Assignment secondAssignment = createAssignment("A2", "Quiz 2", "G1", "2026-04-02");
        StudentMilestones studentMilestones = new StudentMilestones();

        StudentMilestoneView result = milestoneResolver.resolveForStudent(
                VALID_STUDENT_ID,
                List.of(firstAssignment, secondAssignment),
                studentMilestones,
                LocalDate.of(2026, 3, 20)
        );

        assertEquals(new AssignmentId("A1"), result.getMilestones().get(0).getAssignmentId());
        assertEquals(new AssignmentId("A2"), result.getMilestones().get(1).getAssignmentId());
    }

    private Assignment createAssignment(String assignmentId, String label, String group,
                                        String dueDate) {
        return new AssignmentBuilder()
                .withAssignmentId(assignmentId)
                .withLabel(label)
                .withGroup(group)
                .withDueDate(dueDate)
                .build();
    }
}
