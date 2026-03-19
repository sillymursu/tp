package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GroupId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

        // no index specified
        assertParseFailure(parser, "edit /students {Bob; 22222222; bob@example.com; G1}", expectedMessage);

        // no field specified (just index)
        assertParseFailure(parser, "edit /students 1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "edit /students", expectedMessage);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

        // negative index
        assertParseFailure(parser, "edit /students -5 {Bob; 22222222; bob@example.com; G1}", expectedMessage);

        // zero index
        assertParseFailure(parser, "edit /students 0 {Bob; 22222222; bob@example.com; G1}", expectedMessage);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "edit /students 1 some random {Bob; 22222222; bob@example.com; G1}", expectedMessage);

        // wrong command prefix
        assertParseFailure(parser, "add /students 1 {Bob; 22222222; bob@example.com; G1}", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "edit /students 1 {@#$%; 22222222; bob@example.com; G1}", Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, "edit /students 1 {Bob; @#$%; bob@example.com; G1}", Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, "edit /students 1 {Bob; 22222222; invalid-email; G1}", Email.MESSAGE_CONSTRAINTS);

        // multiple invalid values, only first invalid value is captured
        assertParseFailure(parser, "edit /students 1 {@#$%; @#$%; invalid; G1}", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = "edit /students " + targetIndex.getOneBased() + " {Amy Bee; 98765432; amy@example.com; G2}";

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName("Amy Bee")
                .withPhone("98765432")
                .withEmail("amy@example.com")
                .withGroup("G2")
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = "edit /students " + targetIndex.getOneBased() + " {Bob Choo; 22222222; bob@example.com; G1}";

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName("Bob Choo")
                .withPhone("22222222")
                .withEmail("bob@example.com")
                .withGroup("G1")
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

        // Multiple complete edit commands
        assertParseFailure(parser, "edit /students 1 {Bob; 22222222; bob@example.com; G1} " +
                "edit /students 2 {Amy; 98765432; amy@example.com; G2}", expectedMessage);

        // Malformed repeated braces
        assertParseFailure(parser, "edit /students 1 {Bob; 22222222; bob@example.com; G1} " +
                "{Amy; 98765432; amy@example.com; G2}", expectedMessage);
    }

}
