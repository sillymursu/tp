package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String validJson = "/students {Bob Choo; 64123456; bob@example.com; G2}";
        StudentId tempId = new StudentId("S0");
        Person expectedPerson = new PersonBuilder(BOB)
                .withStudentId(tempId.getValue())
                .build(); // No tags in JSON format

        assertParseSuccess(parser, validJson, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validJson = "/students {Bob Choo; 64123456; bob@example.com; G2}";

        // Multiple complete JSON blocks
        assertParseFailure(parser, validJson + " " + validJson,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // JSON + prefix attempts (mixed format)
        assertParseFailure(parser, validJson + " n/Amy Bee",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // Missing opening brace
        assertParseFailure(parser, "/students Name; 64123456; bob@example.com; G1", expectedMessage);

        // Missing closing brace
        assertParseFailure(parser, "/students {Bob Choo; 64123456; bob@example.com; G1", expectedMessage);

        // Missing semicolons (only 3 fields)
        assertParseFailure(parser, "/students {Bob Choo; 64123456; bob@example.com}", expectedMessage);

        // Missing first field (name)
        assertParseFailure(parser, "/students {; 64123456; bob@example.com; G1}", expectedMessage);

        // Wrong command prefix
        assertParseFailure(parser, "/add {Bob Choo; 64123456; bob@example.com; G1}", expectedMessage);

        // All fields jumbled without braces/semicolons
        assertParseFailure(parser, "Bob Choo 64123456 bob@example.com G1", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid values in JSON positions (your ParserUtil will catch these)
        assertParseFailure(parser,
                "/students {@#$%InvalidName; 64123456; bob@example.com; G1}",
                Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                "/students {Bob Choo; @#$%InvalidPhone; bob@example.com; G1}",
                Phone.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                "/students {Bob Choo; 64123456; invalid-email; G1}",
                Email.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, "/students {@#$%Invalid; @#$%Invalid; bob@example.com; G1}",
                Name.MESSAGE_CONSTRAINTS);
    }
}

