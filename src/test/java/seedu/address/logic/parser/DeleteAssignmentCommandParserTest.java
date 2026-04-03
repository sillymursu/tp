package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAssignmentId.ASSIGNMENT_ID_FIRST_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAssignmentCommand;
import seedu.address.model.assignment.AssignmentId;

public class DeleteAssignmentCommandParserTest {

    private final DeleteAssignmentCommandParser parser = new DeleteAssignmentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteAssignmentCommand() {
        assertParseSuccess(parser, " /assignments A1",
                new DeleteAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));

        // Missing prefix
        assertParseFailure(parser, "A1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));

        // Incorrect prefix
        assertParseFailure(parser, " /assignment A1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));

        // Missing assignmentId
        assertParseFailure(parser, " /assignments ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));

        // Invalid assignmentId
        assertParseFailure(parser, " /assignments invalidId",
                AssignmentId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_extraArgs_throwsParseException() {
        assertParseFailure(parser, " /assignments A1 extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));
    }
}
