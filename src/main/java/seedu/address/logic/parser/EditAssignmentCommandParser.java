package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseTuple3AllowEmpty;

import java.util.List;

import seedu.address.logic.commands.EditAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;

/**
 * Parses input arguments and creates a new EditAssignmentCommand object.
 */
public class EditAssignmentCommandParser implements Parser<EditAssignmentCommand> {

    private static final String PATH_ASSIGNMENT = "/assignments";

    /**
     * Parses user input and creates a {@code EditAssignmentCommand}.
     *
     * @param args full argument string supplied after the {@code edit} command word
     * @return a {@code EditAssignmentCommand} containing the parsed assignment id and edit descriptor
     * @throws ParseException if the input does not match {@code /assignment <assignmentId>},
     *         the assignment id is invalid, or if no fields to edit are provided.
     */
    @Override
    public EditAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmed = args.trim();

        if (trimmed.isEmpty() || !trimmed.startsWith(PATH_ASSIGNMENT)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE));
        }

        // Expect: /assignment <assignmentId> {label; group; dueDate}
        String remainder = trimmed.substring(PATH_ASSIGNMENT.length()).trim();
        String[] idAndTuple = remainder.split("\\s+", 2);

        if (idAndTuple.length < 2) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE));
        }

        AssignmentId assignmentId = ParserUtil.parseAssignmentId(idAndTuple[0]);
        List<String> parts = parseTuple3AllowEmpty(idAndTuple[1], EditAssignmentCommand.MESSAGE_USAGE);

        EditAssignmentCommand.EditAssignmentDescriptor descriptor =
                new EditAssignmentCommand.EditAssignmentDescriptor();

        String labelRaw = parts.get(0).trim();
        if (!labelRaw.isEmpty()) {
            descriptor.setLabel(ParserUtil.parseLabel(labelRaw));
        }

        String groupRaw = parts.get(1).trim();
        if (!groupRaw.isEmpty()) {
            descriptor.setGroup(ParserUtil.parseGroup(groupRaw));
        }

        String dueDateRaw = parts.get(2).trim();
        if (!dueDateRaw.isEmpty()) {
            descriptor.setDueDate(ParserUtil.parseDueDate(dueDateRaw));
        }

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAssignmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAssignmentCommand(assignmentId, descriptor);
    }
}
