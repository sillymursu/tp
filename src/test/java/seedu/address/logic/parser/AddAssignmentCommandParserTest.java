package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAssignments.A_TEST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.testutil.AssignmentBuilder;

public class AddAssignmentCommandParserTest {
    private AddAssignmentCommandParser parser = new AddAssignmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String validJson = "/assignments {A-TEST; G1; 2026-04-20}";
        AssignmentId tempId = new AssignmentId("A0");
        Assignment expectedAssignment = new AssignmentBuilder(A_TEST)
                .withAssignmentId(tempId.getValue())
                .build();

        assertParseSuccess(parser, validJson, new AddAssignmentCommand(expectedAssignment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);

        // Missing prefix
        assertParseFailure(parser, "{A-TEST; G1; 2026-04-20}", expectedMessage);

        // Missing opening brace
        assertParseFailure(parser, "/assignments A-TEST; G1; 2026-04-20}", expectedMessage);

        // Missing closing brace
        assertParseFailure(parser, "/assignments {A-TEST; G1; 2026-04-20", expectedMessage);

        // Missing semicolons (only 2 fields)
        assertParseFailure(parser, "/assignments {A-TEST; G1}", expectedMessage);

        // Missing first field (name)
        assertParseFailure(parser, "/assignments {; G1; 2026-04-20}", expectedMessage);

        // Wrong command prefix
        assertParseFailure(parser, "/add {A-TEST; G1; 2026-04-20}", expectedMessage);

        // All fields jumbled without braces/semicolons
        assertParseFailure(parser, "/assignments A-TEST 2026-04-20 G1", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // Invalid dueDate
        assertParseFailure(parser, "/assignments {A-TEST; G1; invalid-date}", DueDate.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_extraFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "/assignments {A-TEST; G1; 2026-04-20; extra}", expectedMessage);

        assertParseFailure(parser, "/assignments {A-TEST; G1; 2026-04-20} extra", expectedMessage);

        assertParseFailure(parser, "/assignments extra {A-TEST; G1; 2026-04-20}", expectedMessage);
    }
}
