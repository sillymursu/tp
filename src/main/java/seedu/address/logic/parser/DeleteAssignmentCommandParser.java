package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;

/**
 * Parses input arguments and creates a new DeleteAssignmentCommand object.
 */
public class DeleteAssignmentCommandParser implements Parser<DeleteAssignmentCommand> {

    private static final String PATH_ASSIGNMENTS = "/assignments";

    /**
     * Parses user input and creates a {@code DeleteAssignmentCommand}.
     *
     * @param args full argument string supplied after the {@code delete} command word.
     * @return a {@code DeleteAssignmentCommand} containing the parsed assignment id.
     * @throws ParseException if the input does not match {@code /assignment <assignmentId>} or
     *         the assignment id is invalid.
     */
    @Override
    public DeleteAssignmentCommand parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (!trimmed.startsWith(PATH_ASSIGNMENTS)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));
        }

        String idText = trimmed.substring(PATH_ASSIGNMENTS.length()).trim();

        if (idText.isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));
        }

        AssignmentId assignmentId = ParserUtil.parseAssignmentId(idText);

        return new DeleteAssignmentCommand(assignmentId);
    }
}
