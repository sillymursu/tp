package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Value object representing a student identifier.
 */
public final class StudentId {
    public static final String MESSAGE_CONSTRAINTS = "StudentId cannot be blank.";

    private final String value;

    /**
     * Constructs a {@code StudentId} with the specified value.
     *
     * @param value The student identifier string.
     * @throws NullPointerException if {@code value} is null.
     * @throws IllegalArgumentException if the trimmed value is blank.
     */
    public StudentId(String value) {
        requireNonNull(value);
        String trimmedValue = value.trim();
        if (trimmedValue.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = trimmedValue;
    }

    /**
     * Returns the string value of this student identifier.
     *
     * @return The student identifier string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if the specified object is equal to this {@code StudentId}.
     * Two {@code StudentId} objects are considered equal if their identifier
     * values are the same.
     *
     * @param other The object to compare with.
     * @return True if both objects represent the same student identifier.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StudentId)) {
            return false;
        }
        StudentId otherStudentId = (StudentId) other;
        return value.equals(otherStudentId.value);
    }

    /**
     * Returns the hash code for this student identifier.
     *
     * @return The hash code based on the identifier value.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns the string representation of this student identifier.
     *
     * @return The student identifier value.
     */
    @Override
    public String toString() {
        return value;
    }
}
