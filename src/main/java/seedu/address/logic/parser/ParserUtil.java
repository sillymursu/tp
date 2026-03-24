package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GroupId;
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
     * Parses a {@code String groupId} into a {@code GroupId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code groupId} is invalid.
     */
    public static GroupId parseGroupId(String groupId) throws ParseException {
        requireNonNull(groupId);
        String trimmedGroupId = groupId.trim();
        if (!GroupId.isValidGroupId(trimmedGroupId)) {
            throw new ParseException(GroupId.MESSAGE_CONSTRAINTS);
        }
        return new GroupId(trimmedGroupId);
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
     * Parses group as a plain String (Group.java is obsolete).
     * Trims leading/trailing spaces.
     *
     * For MVP: allow empty string.
     */
    public static String parseGroup(String group) {
        requireNonNull(group);
        return group.trim();
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
        return new StudentId(trimmedStudentId);
    }
}
