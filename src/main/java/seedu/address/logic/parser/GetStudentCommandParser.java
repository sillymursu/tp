package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.GetStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses "get ..." commands.
 * Supports:
 * - get /students index
 */
public class GetStudentCommandParser implements Parser<Command> {

    public static final String COMMAND_WORD = "get";

    private static final String PATH_ASSIGNMENTS = "/students";

    @Override
    public Command parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmed.split("\\s+");

        if (!parts[0].equals(PATH_ASSIGNMENTS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetStudentCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(parts[1]);
            return new GetStudentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
