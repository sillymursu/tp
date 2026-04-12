package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Sets one milestone record for a student-assignment pair.
 */
public class SetMilestoneCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE =
            "set /students STUDENT_ID /milestones ASSIGNMENT_ID STATUS [COMPLETED_AT]: "
                    + "Sets one milestone for a student.\n"
                    + "Allowed statuses: NOT_STARTED, COMPLETED\n"
                    + "If status is NOT_STARTED, do not provide COMPLETED_AT.\n"
                    + "If status is COMPLETED, COMPLETED_AT is required.\n"
                    + "Example: set /students S1 /milestones A1 NOT_STARTED\n"
                    + "Example: set /students S1 /milestones A1 COMPLETED 2026-03-30 1200";

    public static final String MESSAGE_SUCCESS =
            "Set milestone for student %1$s and assignment %2$s: %3$s, completedAt=%4$s";

    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found: %1$s";
    public static final String MESSAGE_ASSIGNMENT_NOT_FOUND = "Assignment not found: %1$s";
    public static final String MESSAGE_ASSIGNMENT_NOT_IN_STUDENT_GROUP =
            "Assignment %1$s does not belong to student %2$s's group.";

    private final StudentId studentId;
    private final AssignmentId assignmentId;
    private final MilestoneStatus status;
    private final CompletedAt completedAt;

    /**
     * Constructs a {@code SetMilestoneCommand}.
     */
    public SetMilestoneCommand(StudentId studentId, AssignmentId assignmentId,
                               MilestoneStatus status, CompletedAt completedAt) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);
        requireNonNull(status);
        requireNonNull(completedAt);

        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.status = status;
        this.completedAt = completedAt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person student = findStudent(model, studentId);
        if (student == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, studentId));
        }

        Assignment assignment = findAssignment(model, assignmentId);
        if (assignment == null) {
            throw new CommandException(String.format(MESSAGE_ASSIGNMENT_NOT_FOUND, assignmentId));
        }

        if (Collections.disjoint(student.getGroups(), assignment.getGroups())) {
            throw new CommandException(String.format(
                    MESSAGE_ASSIGNMENT_NOT_IN_STUDENT_GROUP, assignmentId, studentId));
        }

        model.setMilestone(studentId, assignmentId, status, completedAt);

        String completedAtValue = completedAt.getValue().isBlank() ? "-" : completedAt.getValue();
        return new CommandResult(String.format(
                MESSAGE_SUCCESS, studentId, assignmentId, status, completedAtValue));
    }

    /**
     * Returns the student with the given student ID, or null if not found.
     */
    private Person findStudent(Model model, StudentId studentId) {
        for (Person person : model.getAddressBook().getPersonList()) {
            if (person.getStudentId().equals(studentId)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Returns the assignment with the given assignment ID, or null if not found.
     */
    private Assignment findAssignment(Model model, AssignmentId assignmentId) {
        for (Assignment assignment : model.getAssignmentList()) {
            if (assignment.getAssignmentId().equals(assignmentId)) {
                return assignment;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SetMilestoneCommand)) {
            return false;
        }

        SetMilestoneCommand otherCommand = (SetMilestoneCommand) other;
        return studentId.equals(otherCommand.studentId)
                && assignmentId.equals(otherCommand.assignmentId)
                && status.equals(otherCommand.status)
                && completedAt.equals(otherCommand.completedAt);
    }
}
