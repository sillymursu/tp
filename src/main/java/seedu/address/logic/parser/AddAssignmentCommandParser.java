package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;

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
        List<String> parts = parseTuple3(remainder);

        Label label = ParserUtil.parseLabel(parts.get(0));
        String group = ParserUtil.parseGroup(parts.get(1));
        DueDate dueDate = ParserUtil.parseDueDate(parts.get(2));

        // placeholder ID, real ID assigned in AddAssignmentCommand.execute()
        Assignment assignment = new Assignment(new AssignmentId("A0"), label, group, dueDate);
        return new AddAssignmentCommand(assignment);
    }

    private List<String> parseTuple3(String raw) throws ParseException {
        String s = raw.trim();

        if (!s.startsWith("{") || !s.endsWith("}")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        String inside = s.substring(1, s.length() - 1).trim();
        if (inside.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        String[] tokens = inside.split(";", -1);
        if (tokens.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        List<String> out = new ArrayList<>();
        for (String t : tokens) {
            out.add(t.trim());
        }

        if (out.get(0).isEmpty() || out.get(2).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        return out;
    }
}
