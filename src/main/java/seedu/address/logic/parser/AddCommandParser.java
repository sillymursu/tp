package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.milestone.StudentId;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * AddCommandPaser class implements the Parser interface
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        // ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
        //                 PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        // if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
        //         || !argMultimap.getPreamble().isEmpty()) {
        //     throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        // }
        // argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        // Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        // Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        // Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        // Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        // Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        // Temporary placeholder. Actual StudentId is assigned in AddCommand.execute().
        StudentId tempId = new StudentId("S0");
        String[] parts = args.split("\\{")[1].split(",");
        if (parts.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(parts[0]);
        Phone phone = ParserUtil.parsePhone(parts[1]);
        Email email = ParserUtil.parseEmail(parts[2]);
        Address address = ParserUtil.parseAddress("Singapore"); // Temporary placeholder
        Set<Tag> tag = new HashSet<Tag>(); //No tags for LeGoat
        Person person = new Person(tempId, name, phone, email, address, tag);

        return new AddCommand(person);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
