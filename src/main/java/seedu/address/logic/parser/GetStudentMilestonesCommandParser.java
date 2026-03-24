package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.GetStudentMilestonesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new GetStudentMilestonesCommand object.
 */
public class GetStudentMilestonesCommandParser implements Parser<GetStudentMilestonesCommand> {

    @Override
    public GetStudentMilestonesCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        String prefix = "/students ";
        String suffix = " /milestones";

        if (!trimmedArgs.startsWith(prefix) || !trimmedArgs.endsWith(suffix)) {
            throw new ParseException(String.format(
                    seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentMilestonesCommand.MESSAGE_USAGE));
        }

        String studentIdString = trimmedArgs.substring(
                prefix.length(),
                trimmedArgs.length() - suffix.length()
        ).trim();

        if (studentIdString.isEmpty()) {
            throw new ParseException(String.format(
                    seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentMilestonesCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(studentIdString);
        return new GetStudentMilestonesCommand(studentId);
    }
}
