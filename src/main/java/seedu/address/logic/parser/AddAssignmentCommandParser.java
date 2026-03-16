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
 * adda {<label>, <group>, <dueDate>}
 *
 * Example:
 * adda {A-JUnit, Sec3A, 2026-02-20}
 */
public class AddAssignmentCommandParser implements Parser<AddAssignmentCommand> {

    @Override
    public AddAssignmentCommand parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        List<String> parts = parseTuple3(trimmed);

        String labelStr = parts.get(0);
        String groupStr = parts.get(1);
        String dueDateStr = parts.get(2);

        Label label = ParserUtil.parseLabel(labelStr);
        String group = ParserUtil.parseGroup(groupStr);
        DueDate dueDate = ParserUtil.parseDueDate(dueDateStr);

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

        // Split by commas, trim each field
        String[] tokens = inside.split(",", -1); // keep empty tokens if user writes ", ,"
        if (tokens.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        List<String> out = new ArrayList<>();
        for (String t : tokens) {
            out.add(t.trim());
        }

        // Basic check: label and dueDate must not be empty
        if (out.get(0).isEmpty() || out.get(2).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        return out;
    }
}