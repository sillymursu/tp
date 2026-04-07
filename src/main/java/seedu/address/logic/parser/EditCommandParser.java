package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private static final Pattern STUDENT_EDIT_ARGS_FORMAT = Pattern.compile(
            "^\\s*/students\\s+(?<studentId>\\S+)\\s*\\{\\s*(?<name>[^;{}]*"
                    + ")\\s*;\\s*(?<phone>[^;{}]*)\\s*;\\s*(?<email>[^;]*)\\s*;"
                    + "\\s*(?<group>[^;]*)\\s*\\}\\s*$"
    );


    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Matcher matcher = STUDENT_EDIT_ARGS_FORMAT.matcher(args);
            if (!matcher.matches()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditCommand.MESSAGE_USAGE));
            }
            StudentId studentId = ParserUtil.parseStudentId(matcher.group("studentId"));

            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
            if (!matcher.group("name").isEmpty()) {
                editPersonDescriptor.setName(ParserUtil.parseName(matcher.group("name").trim()));
            }
            if (!matcher.group("phone").isEmpty()) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(matcher.group("phone").trim()));
            }
            if (!matcher.group("email").isEmpty()) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(matcher.group("email").trim()));
            }
            if (!matcher.group("group").isEmpty()) {
                String[] groupParts = matcher.group("group")
                        .split("\\s*,\\s*");
                editPersonDescriptor.setGroups(ParserUtil.parseGroups(groupParts));
            }

            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }

            return new EditCommand(studentId, editPersonDescriptor);
        } catch (ParseException pe) {
            throw pe;
        }
    }


}
