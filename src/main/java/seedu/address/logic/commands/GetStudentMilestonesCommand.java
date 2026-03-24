package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.milestone.ResolvedMilestone;
import seedu.address.model.milestone.StudentMilestoneView;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Shows the resolved milestone view for one student.
 */
public class GetStudentMilestonesCommand extends Command {

    public static final String COMMAND_WORD = "get";

    public static final String MESSAGE_USAGE =
            "get /students <studentId> /milestones: Shows milestone progress for a student.\n"
                    + "Example: get /students S001 /milestones";

    private static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found: %s";
    private static final String MESSAGE_NO_ASSIGNMENTS = "There are no assignments in the system.";

    private final StudentId studentId;

    public GetStudentMilestonesCommand(StudentId studentId) {
        requireNonNull(studentId);
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person student = findStudentById(model, studentId);
        if (student == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, studentId));
        }

        List<Assignment> assignments = model.getAssignmentList();
        if (assignments.isEmpty()) {
            return new CommandResult(MESSAGE_NO_ASSIGNMENTS);
        }

        StudentMilestoneView studentMilestoneView = model.getResolvedMilestones(studentId);
        String resultMessage = formatStudentMilestones(student, studentMilestoneView);

        return new CommandResult(resultMessage);
    }

    /**
     * Finds the student with the given student ID from the model's student list.
     */
    private Person findStudentById(Model model, StudentId studentId) {
        for (Person person : model.getAddressBook().getPersonList()) {
            if (person.getStudentId().equals(studentId)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Formats the resolved milestone view into a readable CLI result.
     */
    private String formatStudentMilestones(Person student, StudentMilestoneView studentMilestoneView) {
        StringBuilder sb = new StringBuilder();

        sb.append("Milestones for ")
                .append(student.getName())
                .append(" (")
                .append(studentId)
                .append(")");

        for (ResolvedMilestone resolvedMilestone : studentMilestoneView.getMilestones()) {
            sb.append("\n")
                    .append(resolvedMilestone.getAssignmentId())
                    .append(" | ")
                    .append(resolvedMilestone.getResolvedStatus())
                    .append(" | due=")
                    .append(resolvedMilestone.getDueDate())
                    .append(" | completedAt=")
                    .append(formatCompletedAt(resolvedMilestone));
        }

        return sb.toString();
    }

    /**
     * Returns a printable completedAt value.
     */
    private String formatCompletedAt(ResolvedMilestone resolvedMilestone) {
        String value = resolvedMilestone.getCompletedAt().getValue();
        return value.isBlank() ? "-" : value;
    }
}
