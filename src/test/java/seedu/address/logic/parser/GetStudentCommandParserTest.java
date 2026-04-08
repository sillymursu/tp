package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudentId.STUDENT_ID_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetStudentCommand;
import seedu.address.logic.commands.GetStudentsCommand;

public class GetStudentCommandParserTest {

    private GetStudentCommandParser parser = new GetStudentCommandParser();

    @Test
    public void parse_validArgs_returnsGetStudentCommand() {
        assertParseSuccess(parser, " /students S1",
                new GetStudentCommand(STUDENT_ID_FIRST_PERSON));
    }

    @Test
    public void parse_validArgs_returnsGetStudentsCommand() {
        assertParseSuccess(parser, " /students",
                new GetStudentsCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetStudentCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " /assignments ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetStudentCommand.MESSAGE_USAGE));
        // Invalid studentId
        assertParseFailure(parser, " /students invalidId",
                MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_extraArgs_throwsParseException() {
        assertParseFailure(parser, " /students S1 extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetStudentCommand.MESSAGE_USAGE));
    }
}
