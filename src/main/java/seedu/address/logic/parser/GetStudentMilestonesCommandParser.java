package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.GetStudentMilestonesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new GetStudentMilestonesCommand object.
 */
public class GetStudentMilestonesCommandParser implements Parser<GetStudentMilestonesCommand> {

    private static final String PREFIX = "/students";
    private static final String SUFFIX = "/milestones";

    @Override
    public GetStudentMilestonesCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        if (!trimmedArgs.startsWith(PREFIX) || !trimmedArgs.endsWith(SUFFIX)) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentMilestonesCommand.MESSAGE_USAGE));
        }

        String middle = trimmedArgs.substring(
                PREFIX.length(),
                trimmedArgs.length() - SUFFIX.length()).trim();

        if (middle.isEmpty() || middle.contains(" ")) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentMilestonesCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(middle);
        return new GetStudentMilestonesCommand(studentId);
    }
}
