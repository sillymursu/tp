package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetStudentMilestonesCommand;
import seedu.address.model.person.StudentId;

public class GetStudentMilestonesCommandParserTest {

    private final GetStudentMilestonesCommandParser parser = new GetStudentMilestonesCommandParser();

    @Test
    public void parse_validArgs_returnsGetStudentMilestonesCommand() {
        assertParseSuccess(parser,
                "/students S1 /milestones",
                new GetStudentMilestonesCommand(new StudentId("S1")));
    }

    @Test
    public void parse_missingStudentId_throwsParseException() {
        assertParseFailure(parser,
                "/students /milestones",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetStudentMilestonesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongSuffix_throwsParseException() {
        assertParseFailure(parser,
                "/students S1 /milestone",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetStudentMilestonesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraArguments_throwsParseException() {
        assertParseFailure(parser,
                "/students S1 /milestones extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetStudentMilestonesCommand.MESSAGE_USAGE));
    }
}
