package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Group;
import seedu.address.model.assignment.Label;
import seedu.address.model.assignment.Order;

/**
 * Parses input arguments and creates a new AddAssignmentCommand object.
 */
public class AddAssignmentCommandParser implements Parser<AddAssignmentCommand> {

    @Override
    public AddAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LABEL, PREFIX_GROUP, PREFIX_DUE_DATE, PREFIX_ORDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_LABEL, PREFIX_ORDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_LABEL, PREFIX_GROUP, PREFIX_DUE_DATE, PREFIX_ORDER);

        Label label = ParserUtil.parseLabel(argMultimap.getValue(PREFIX_LABEL).get());
        Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).orElse(""));
        DueDate dueDate = ParserUtil.parseDueDate(argMultimap.getValue(PREFIX_DUE_DATE).orElse(""));
        Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_ORDER).get());

        Assignment assignment = new Assignment(label, group, dueDate, order);
        return new AddAssignmentCommand(assignment);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return java.util.stream.Stream.of(prefixes)
                .allMatch(prefix -> argMultimap.getValue(prefix).isPresent());
    }
}
