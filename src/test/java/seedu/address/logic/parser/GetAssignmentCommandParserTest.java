package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAssignmentId.ASSIGNMENT_ID_FIRST_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetAssignmentCommand;
import seedu.address.logic.commands.GetAssignmentsCommand;
import seedu.address.model.assignment.AssignmentId;

public class GetAssignmentCommandParserTest {

    private GetAssignmentCommandParser parser = new GetAssignmentCommandParser();

    @Test
    public void parse_validArgs_returnsGetAssignmentCommand() {
        assertParseSuccess(parser, " /assignments A1",
                new GetAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT));
    }

    @Test
    public void parse_validArgs_returnsGetAssignmentsCommand() {
        assertParseSuccess(parser, " /assignments",
                new GetAssignmentsCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAssignmentsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " /students ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAssignmentsCommand.MESSAGE_USAGE));

        // Invalid assignmentId
        assertParseFailure(parser, " /assignments invalidId",
                AssignmentId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_extraArgs_throwsParseException() {
        assertParseFailure(parser, " /assignments A1 extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAssignmentCommand.MESSAGE_USAGE));
    }
}
