package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudentId.STUDENT_ID_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "S1" and "S1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " /students S1",
                new DeleteCommand(STUDENT_ID_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " /students a", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStudentIdFormat_throwsParseException() {
        assertParseFailure(parser, " /students student1",
                MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_incorrectLengthOfArgs_throwsParseException() {
        assertParseFailure(parser, " /students",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " /students S1 S2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE));
    }
}
