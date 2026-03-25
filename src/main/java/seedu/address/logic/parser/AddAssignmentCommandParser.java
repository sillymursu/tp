package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseTuple3;

import java.util.List;

import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.group.Group;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new AddAssignmentCommand object.
 *
 * Expected format:
 * add /assignment {label, group, dueDate}
 *
 * Example:
 * add /assignment {A-JUnit, Sec3A, 2026-02-20}
 */
public class AddAssignmentCommandParser implements Parser<AddAssignmentCommand> {

    private static final String PATH_ASSIGNMENT = "/assignment";

    @Override
    public AddAssignmentCommand parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        // Must start with "/assignment"
        if (!trimmed.startsWith(PATH_ASSIGNMENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        String remainder = trimmed.substring(PATH_ASSIGNMENT.length()).trim();
        List<String> parts = parseTuple3(remainder, AddAssignmentCommand.MESSAGE_USAGE);

        Label label = ParserUtil.parseLabel(parts.get(0));
        Group group = ParserUtil.parseGroup(parts.get(1));
        DueDate dueDate = ParserUtil.parseDueDate(parts.get(2));

        // placeholder ID, real ID assigned in AddAssignmentCommand.execute()
        Assignment assignment = new Assignment(new AssignmentId("A0"), label, group, dueDate);
        return new AddAssignmentCommand(assignment);
    }
}
