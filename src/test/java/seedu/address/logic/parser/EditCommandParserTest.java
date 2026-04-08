package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

        // no index specified
        assertParseFailure(parser, "/students {Bob; 22222222; bob@example.com; G1}", expectedMessage);

        //        // no field specified (just index)
        //        assertParseFailure(parser, "edit /students 1", EditCommand.MESSAGE_USAGE);

        // no index and no field specified
        assertParseFailure(parser, "/students", expectedMessage);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedIndexMessage = MESSAGE_INVALID_INDEX;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

        // negative index
        assertParseFailure(parser, "/students S-5 {Bob; 22222222; bob@example.com; G1}",
                expectedIndexMessage);

        // zero index
        assertParseFailure(parser, "/students S0 {Bob; 22222222; bob@example.com; G1}",
                expectedIndexMessage);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "/students S1 some random {Bob; 22222222; bob@example.com; G1}",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "/students S1 {@#$%; 98123456; invalid; G1}", Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, "/students S1 {Bob; @#$%; bob@example.com; G1}", Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, "/students S1 {Bob; 98123456; invalid-email; G1}", Email.MESSAGE_CONSTRAINTS);

        // multiple invalid values, only first invalid value is captured
        assertParseFailure(parser, "/students S1 {@#$%; @#$%; invalid; G1}", Name.MESSAGE_CONSTRAINTS);
    }

    //    @Test
    //    public void parse_allFieldsSpecified_success() {
    //        Index targetIndex = INDEX_SECOND_PERSON;
    //        String userInput = "edit /students " + targetIndex.getOneBased()
    //          + " {Amy Bee; 98765432; amy@example.com; G2}";
    //
    //        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
    //                .withName("Amy Bee")
    //                .withPhone("98765432")
    //                .withEmail("amy@example.com")
    //                .withGroup("G2")
    //                .build();
    //        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
    //
    //        assertParseSuccess(parser, userInput, expectedCommand);
    //    }
    //    @Test
    //    public void parse_someFieldsSpecified_success() {
    //        Index targetIndex = INDEX_FIRST_PERSON;
    //        String userInput = "edit /students " + targetIndex.getOneBased()
    //          + " {Bob Choo; 22222222; bob@example.com: G2}";
    //
    //        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
    //                .withName("Bob Choo")
    //                .withPhone("22222222")
    //                .withEmail("bob@example.com")
    //                .withGroup("G1")
    //                .build();
    //        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
    //
    //        assertParseSuccess(parser, userInput, expectedCommand);
    //    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

        // Multiple complete edit commands
        assertParseFailure(parser, "/students S1 {Bob; 22222222; bob@example.com; G1} "
                + "edit /students S2 {Amy; 98765432; amy@example.com; G2}", expectedMessage);

        // Malformed repeated braces
        assertParseFailure(parser, "/students S1 {Bob; 22222222; bob@example.com; G1} "
                + "{Amy; 98765432; amy@example.com; G2}", expectedMessage);
    }

}
