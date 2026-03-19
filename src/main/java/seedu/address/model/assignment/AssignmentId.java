package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Assignment's ID (e.g., A301).
 * Guarantees: immutable; is valid as declared in {@link #isValidAssignmentId(String)}.
 */
public class AssignmentId {

    public static final String MESSAGE_CONSTRAINTS =
            "AssignmentId must be in the format A followed by 1 to 4 digits (e.g., A1, A301, A9999).";

    // A + 1 to 4 digits
    private static final String VALIDATION_REGEX = "A\\d{1,4}";

    private final String value;

    /**
     * Constructs an {@code AssignmentId}.
     *
     * @param value A valid assignment id.
     */
    public AssignmentId(String value) {
        requireNonNull(value);
        String trimmed = value.trim();
        if (!isValidAssignmentId(trimmed)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = trimmed;
    }

    /**
     * Returns the string value of this assignment id.
     *
     * @return The assignment id as a string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if a given string is a valid assignment id.
     *
     * @param test The string to validate.
     * @return {@code true} if {@code test} is a valid assignment id, {@code false} otherwise.
     */
    public static boolean isValidAssignmentId(String test) {
        if (test == null) {
            return false;
        }
        return test.trim().matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of this assignment id.
     *
     * @return The assignment id as a string.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if both assignment ids have the same value.
     *
     * @param other The object to compare against.
     * @return {@code true} if this assignment id is equal to the other object, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AssignmentId)) {
            return false;
        }
        AssignmentId otherId = (AssignmentId) other;
        return value.equals(otherId.value);
    }

    /**
     * Returns the hash code value of this assignment id.
     *
     * @return The hash code of this assignment id.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
