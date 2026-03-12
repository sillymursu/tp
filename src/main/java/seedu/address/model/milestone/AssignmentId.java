package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Value object representing an assignment identifier.
 */
public final class AssignmentId {
    public static final String MESSAGE_CONSTRAINTS = "AssignmentId cannot be blank.";

    private final String value;

    /**
     * Constructs an {@code AssignmentId} with the given value.
     *
     * @param value The assignment identifier string.
     * @throws NullPointerException if {@code value} is null.
     * @throws IllegalArgumentException if the trimmed value is blank.
     */
    public AssignmentId(String value) {
        requireNonNull(value);
        String trimmedValue = value.trim();
        if (trimmedValue.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = trimmedValue;
    }

    /**
     * Returns the string value of this assignment identifier.
     *
     * @return The assignment identifier as a string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if the given object is equal to this assignment identifier.
     *
     * @param other The object to compare against.
     * @return True if {@code other} is an {@code AssignmentId} with the same value.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AssignmentId)) {
            return false;
        }
        AssignmentId otherAssignmentId = (AssignmentId) other;
        return value.equals(otherAssignmentId.value);
    }

    /**
     * Returns the hash code of this assignment identifier.
     *
     * @return The hash code based on the identifier value.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns the string representation of this assignment identifier.
     *
     * @return The assignment identifier value.
     */
    @Override
    public String toString() {
        return value;
    }
}
