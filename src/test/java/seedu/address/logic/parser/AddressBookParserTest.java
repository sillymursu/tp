package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudentId.STUDENT_ID_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {
    private static final String GET_MESSAGE_USAGE =
            "get: Retrieves a student, assignment, or milestone view.\n"
                    + "Use one of:\n"
                    + "1. get /students STUDENT_ID\n"
                    + "2. get /students STUDENT_ID /milestones\n"
                    + "3. get /assignments ASSIGNMENT_ID\n"
                    + "Examples:\n"
                    + "get /students S1\n"
                    + "get /students S1 /milestones\n"
                    + "get /assignments A1";
    private static final String SET_MESSAGE_USAGE =
            "set: Updates one milestone for a student.\n"
                    + "Format:\n"
                    + "set /students STUDENT_ID /milestones ASSIGNMENT_ID STATUS [COMPLETED_AT]\n"
                    + "Allowed statuses: NOT_STARTED, COMPLETED\n"
                    + "If status is NOT_STARTED, do not provide COMPLETED_AT.\n"
                    + "If status is COMPLETED, COMPLETED_AT is required.\n"
                    + "Examples:\n"
                    + "set /students S1 /milestones A1 NOT_STARTED\n"
                    + "set /students S1 /milestones A1 COMPLETED 2026-03-30 1200";

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " /students " + STUDENT_ID_FIRST_PERSON);
        assertEquals(new DeleteCommand(STUDENT_ID_FIRST_PERSON), command);
    }

    //    @Test
    //    public void parseCommand_edit() throws Exception {
    //        Person person = new PersonBuilder().build();
    //        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
    //        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
    //                + " /students " + INDEX_FIRST_PERSON.getOneBased() + " "
    //                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
    //        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    //    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " /students "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_getInvalidFormat_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GET_MESSAGE_USAGE), (
                ) -> parser.parseCommand("get /hello"));
    }

    @Test
    public void parseCommand_setInvalidFormat_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SET_MESSAGE_USAGE), (
                ) -> parser.parseCommand("set /students S1"));
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SET_MESSAGE_USAGE), (
                ) -> parser.parseCommand("set /assignments A1"));
        assertThrows(ParseException.class,
                "Completed date must be in the format YYYY-MM-DD HHMM", (
                ) -> parser.parseCommand("set /students S8 /milestones A1 COMPLETED 2026-05-32 0900"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
