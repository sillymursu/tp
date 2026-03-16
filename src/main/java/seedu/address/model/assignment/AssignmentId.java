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

    public String getValue() {
        return value;
    }

    /**
     * Returns true if a given string is a valid assignment id.
     */
    public static boolean isValidAssignmentId(String test) {
        if (test == null) {
            return false;
        }
        return test.trim().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

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

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}