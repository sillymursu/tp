package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Locale;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.SetMilestoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new SetMilestoneCommand object.
 */
public class SetMilestoneCommandParser implements Parser<SetMilestoneCommand> {

    private static final String STUDENTS_PREFIX = "/students";
    private static final String MILESTONES_SEGMENT = "/milestones";
    private static final String MESSAGE_COMPLETED_DATE_FORMAT_CONSTRAINTS =
            "Completed date must be in the format YYYY-MM-DD HHMM";

    @Override
    public SetMilestoneCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        if (!trimmedArgs.startsWith(STUDENTS_PREFIX)) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SetMilestoneCommand.MESSAGE_USAGE));
        }

        int milestonesIndex = trimmedArgs.indexOf(MILESTONES_SEGMENT);
        if (milestonesIndex == -1) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SetMilestoneCommand.MESSAGE_USAGE));
        }

        String leftPart = trimmedArgs.substring(0, milestonesIndex).trim();
        String rightPart = trimmedArgs.substring(milestonesIndex + MILESTONES_SEGMENT.length()).trim();

        String[] leftTokens = leftPart.split("\\s+");
        if (leftTokens.length != 2 || !leftTokens[0].equals(STUDENTS_PREFIX)) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SetMilestoneCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(leftTokens[1]);

        String[] rightTokens = rightPart.split("\\s+");

        if (rightTokens.length < 2 || rightTokens.length > 4) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SetMilestoneCommand.MESSAGE_USAGE));
        }

        AssignmentId assignmentId = ParserUtil.parseAssignmentId(rightTokens[0]);
        String rawStatus = rightTokens[1].trim().toUpperCase(Locale.ROOT);

        MilestoneStatus status = parseMilestoneStatus(rawStatus);
        CompletedAt completedAt = parseCompletedAt(status, rightTokens);

        return new SetMilestoneCommand(studentId, assignmentId, status, completedAt);
    }

    /**
     * Parses the milestone status, restricting it to the two allowed stored states.
     */
    private MilestoneStatus parseMilestoneStatus(String rawStatus) throws ParseException {
        if (rawStatus.equals("NOT_STARTED")) {
            return MilestoneStatus.NOT_STARTED;
        }

        if (rawStatus.equals("COMPLETED")) {
            return MilestoneStatus.COMPLETED;
        }

        throw new ParseException(String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SetMilestoneCommand.MESSAGE_USAGE));
    }

    /**
     * Parses completedAt according to the chosen status.
     *
     * <p>Rules:
     * <ul>
     *   <li>NOT_STARTED must not have a completedAt value</li>
     *   <li>COMPLETED must have a completedAt value</li>
     * </ul>
     */
    private CompletedAt parseCompletedAt(MilestoneStatus status, String[] rightTokens) throws ParseException {
        if (status == MilestoneStatus.NOT_STARTED) {
            if (rightTokens.length != 2) {
                throw new ParseException(String.format(
                        Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        SetMilestoneCommand.MESSAGE_USAGE));
            }
            return new CompletedAt("");
        }

        if (status == MilestoneStatus.COMPLETED) {
            if (rightTokens.length != 4) {
                throw new ParseException(String.format(
                        Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        SetMilestoneCommand.MESSAGE_USAGE));
            }
            String completedDate = rightTokens[2].trim() + "T" + rightTokens[3].trim() + "H";
            if (!CompletedAt.isValidCompletedAtFormat(completedDate)) {
                throw new ParseException(MESSAGE_COMPLETED_DATE_FORMAT_CONSTRAINTS);
            }
            if (!CompletedAt.isValidCompletedAt(completedDate)) {
                throw new ParseException(MESSAGE_COMPLETED_DATE_FORMAT_CONSTRAINTS);
            }
            return new CompletedAt(completedDate);
        }

        throw new ParseException(String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SetMilestoneCommand.MESSAGE_USAGE));
    }
}
