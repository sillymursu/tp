package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.GetStudentCommand;
import seedu.address.logic.commands.GetStudentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses {@code get} commands for retrieving a student by student ID.
 * Supports:
 * <ul>
 *   <li>{@code get /students <studentId>}</li>
 * </ul>
 */
public class GetStudentCommandParser implements Parser<Command> {

    public static final String COMMAND_WORD = "get";

    private static final String PATH_STUDENTS = "/students";

    @Override
    public Command parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmed.split("\\s+");

        if (!parts[0].equals(PATH_STUDENTS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentCommand.MESSAGE_USAGE));
        }
        if (parts.length == 1) {
            return new GetStudentsCommand();
        }
        if (parts.length == 2) {
            StudentId studentId = ParserUtil.parseStudentId(parts[1]);
            return new GetStudentCommand(studentId);
        }
        // Too many arguments after /assignments should use the single-assignment usage.
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GetStudentCommand.MESSAGE_USAGE));
    }
}
