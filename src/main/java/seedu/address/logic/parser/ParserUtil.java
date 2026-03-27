package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String label} into a {@code Label}.
     */
    public static Label parseLabel(String label) throws ParseException {
        requireNonNull(label);
        String trimmed = label.trim();
        if (!Label.isValidLabel(trimmed)) {
            throw new ParseException(Label.MESSAGE_CONSTRAINTS);
        }
        return new Label(trimmed);
    }

    /**
     * Parses a {@code String assignmentId} into an {@code AssignmentId}.
     */
    public static AssignmentId parseAssignmentId(String assignmentId) throws ParseException {
        requireNonNull(assignmentId);
        String trimmed = assignmentId.trim();
        if (!AssignmentId.isValidAssignmentId(trimmed)) {
            throw new ParseException(AssignmentId.MESSAGE_CONSTRAINTS);
        }
        return new AssignmentId(trimmed);
    }
    /**
     * Parses a {@code String group} into a {@code Group}.
     */
    public static Group parseGroup(String group) throws ParseException {
        requireNonNull(group);
        String trimmed = group.trim();
        if (!Group.isValidGroup(trimmed)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }

        return new Group(trimmed);
    }
    /**
     * Parses a {@code String[] group} into a {@code Set<Group>}.
     */
    public static Set<Group> parseGroups(String[] groups) throws ParseException {
        requireNonNull(groups);
        final Set<Group> groupSet = new HashSet<>();
        for (String groupName : groups) {
            groupSet.add(parseGroup(groupName));
        }
        return groupSet;
    }
    /**
     * Parses a {@code String dueDate} into a {@code DueDate}.
     */
    public static DueDate parseDueDate(String dueDate) throws ParseException {
        requireNonNull(dueDate);
        String trimmed = dueDate.trim();
        if (!DueDate.isValidDate(trimmed)) {
            throw new ParseException(DueDate.MESSAGE_CONSTRAINTS);
        }
        return new DueDate(trimmed);
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!trimmedStudentId.matches("S[1-9]\\d*")) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String raw} into a List of 3 Strings, split by ';' and enclosed in '{}'.
     * Allows individual elements within the tuple to be empty strings.
     *
     * @param raw The string to be parsed.
     * @param errorMessage The specific command usage message to display if parsing fails.
     * @return A list containing exactly 3 trimmed strings.
     * @throws ParseException if the input is null, not enclosed in '{}', or does not contain exactly 3 elements.
     */
    public static List<String> parseTuple3AllowEmpty(String raw, String errorMessage) throws ParseException {
        if (raw == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }

        String s = raw.trim();

        if (!s.startsWith("{") || !s.endsWith("}")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }

        // Strip the curly braces
        String inside = s.substring(1, s.length() - 1);

        // Split by ';' keeping empty trailing tokens
        String[] tokens = inside.split(";", -1);

        if (tokens.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }

        return Arrays.asList(tokens[0].trim(), tokens[1].trim(), tokens[2].trim());
    }

    /**
     * Parses a {@code String raw} into a List of 3 Strings, split by ';' and enclosed in '{}'.
     * Validates that the first and third elements are strictly not empty.
     *
     * @param raw The string to be parsed.
     * @param errorMessage The specific command usage message to display if parsing fails.
     * @return A list containing exactly 3 trimmed strings.
     * @throws ParseException if the input format is invalid, or if the 1st or 3rd elements are empty.
     */
    public static List<String> parseTuple3(String raw, String errorMessage) throws ParseException {
        // Delegate the initial parsing to the allowEmpty method
        List<String> parsedList = parseTuple3AllowEmpty(raw, errorMessage);

        // Perform the strict validation for the 1st and 3rd elements
        if (parsedList.get(0).isEmpty() || parsedList.get(2).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }

        return parsedList;
    }
}
