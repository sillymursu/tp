package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.model.group.GroupName;

public class FindGroupCommandParserTest {

    private FindGroupCommandParser parser = new FindGroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCommandWord_throwsParseException() {
        assertParseFailure(parser, "/groupyy",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindGroupCommand expectedFindCommand =
                new FindGroupCommand(new GroupName("Sec3A"));
        assertParseSuccess(parser, " /groups Sec3A",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n /groups \n Sec3A \t", expectedFindCommand);
    }

}
