package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the group for an Assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidGroup(String)}.
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS = "Group can take any values, and it should not be blank";

    /*
     * The first character of the label must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String group;

    /**
     * Constructs a {@code Group}.
     *
     * @param group A valid group.
     */
    public Group(String group) {
        requireNonNull(group);
        checkArgument(isValidGroup(group), MESSAGE_CONSTRAINTS);
        this.group = group;
    }

    /**
     * Returns true if a given string is a valid group.
     *
     * @param test The string to validate.
     * @return {@code true} if {@code test} is a valid group, {@code false} otherwise.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of this group.
     *
     * @return The group as a string.
     */
    @Override
    public String toString() {
        return group;
    }

    /**
     * Returns true if both groups have the same value.
     *
     * @param other The object to compare against.
     * @return {@code true} if this group is equal to the other object, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return group.equals(otherGroup.group);
    }

    /**
     * Returns the hash code value of this group.
     *
     * @return The hash code of this group.
     */
    @Override
    public int hashCode() {
        return group.hashCode();
    }
}
