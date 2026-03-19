package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.GetAssignmentCommand;
import seedu.address.logic.commands.GetAssignmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;


/**
 * Parses "get ..." commands.
 * Supports:
 * - get /assignments
 * - get /assignments assignmentId
 */
public class GetAssignmentCommandParser implements Parser<Command> {

    public static final String COMMAND_WORD = "get";

    private static final String PATH_ASSIGNMENTS = "/assignments";

    @Override
    public Command parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetAssignmentCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmed.split("\\s+");

        if (parts[0].equals(PATH_ASSIGNMENTS)) {
            // get /assignments
            if (parts.length == 1) {
                return new GetAssignmentsCommand();
            }

            // get /assignments <assignmentId>
            if (parts.length == 2) {
                return new GetAssignmentCommand(new AssignmentId(parts[1]));
            }

        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetAssignmentsCommand.MESSAGE_USAGE));
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAssignmentsCommand.MESSAGE_USAGE));
    }
}
