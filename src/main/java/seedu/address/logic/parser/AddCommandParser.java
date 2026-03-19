package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;

/**
 * AddCommandPaser class implements the Parser interface
 */
public class AddCommandParser implements Parser<AddCommand> {
    private static final Pattern ADD_PATTERN = Pattern.compile(
            "^\\s*/students\\s*\\{\\s*(?<name>[^,{}]+?)\\s*;\\s*(?<phone>[^,{}]+?)\\s*;"
                    +
                    "\\s*(?<email>[^,{}]+?)\\s*;\\s*(?<group>[^,{}]+?)\\s*\\}\\s*$"
    );
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        Matcher matcher = ADD_PATTERN.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        StudentId tempId = new StudentId("S0");
        Name name = ParserUtil.parseName(matcher.group("name"));
        Phone phone = ParserUtil.parsePhone(matcher.group("phone"));
        Email email = ParserUtil.parseEmail(matcher.group("email"));
        String group = ParserUtil.parseGroup(matcher.group("group"));
        Person person = new Person(tempId, name, phone, email, group);

        return new AddCommand(person);
    }
}
