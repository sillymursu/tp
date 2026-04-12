package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAssignmentId.ASSIGNMENT_ID_FIRST_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAssignmentCommand;
import seedu.address.logic.commands.EditAssignmentCommand.EditAssignmentDescriptor;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.testutil.EditAssignmentDescriptorBuilder;

public class EditAssignmentCommandParserTest {

    private EditAssignmentCommandParser parser = new EditAssignmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = "/assignments A1 {A-TEST; G1; 2026-04-20}";

        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder()
                .withLabel("A-TEST")
                .withGroup("G1")
                .withDueDate("2026-04-20")
                .build();

        EditAssignmentCommand expectedCommand = new EditAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsPresent_success() {
        String userInput = "/assignments A1 {New Label; ; }";

        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder()
                .withLabel("New Label")
                .build();

        EditAssignmentCommand expectedCommand = new EditAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);

        // Missing opening brace
        assertParseFailure(parser, "/assignments Label; G1; 2026-04-20}", expectedMessage);

        // Missing closing brace
        assertParseFailure(parser, "/assignments {Label; G1; 2026-04-20", expectedMessage);

        // No assignmentId specified
        assertParseFailure(parser, "/assignments {Label; G1; 2026-04-20}", expectedMessage);

        // No fields specified (just assignmentId)
        assertParseFailure(parser, "/assignments A1", expectedMessage);

        // Missing semicolons (only 2 fields)
        assertParseFailure(parser, "/assignments {Label; G1}", expectedMessage);

        // Incorrect command word
        assertParseFailure(parser, "/students A1 {Label; G1; 2026-04-20}", expectedMessage);

        // No fields specified
        assertParseFailure(parser, "/assignments A1 { ; ; }", EditAssignmentCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidValue_failure() {

        // Invalid assignmentId
        assertParseFailure(parser, "/assignments invalid {Label; G1; 2026-04-20}", AssignmentId.MESSAGE_CONSTRAINTS);

        // Invalid dueDate
        assertParseFailure(parser, "/assignments A1 {Label; G1; 2026-99-99}", DueDate.MESSAGE_CONSTRAINTS_DATE);
    }

}
