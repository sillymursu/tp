package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Value object representing the group identifier a student belongs to.
 */
public final class GroupId {
    public static final String MESSAGE_CONSTRAINTS = "GroupName cannot be blank.";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String identifier;

    /**
     * Constructs a {@code GroupName}.
     *
     * @param identifier A valid, non-blank group identifier string.
     */
    public GroupId(String identifier) {
        requireNonNull(identifier);
        if (!isValidGroupId(identifier)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.identifier = identifier;
    }

    /**
     * Returns true if the given string is a valid group identifier.
     */
    public static boolean isValidGroupId(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return identifier;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GroupId)) {
            return false;
        }
        GroupId otherGroupId = (GroupId) other;
        return identifier.equals(otherGroupId.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return identifier;
    }
}
