package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Value object representing a completion timestamp.
 * Valid values are either an empty string or a timestamp in the format YYYY-MM-DDTHHMMH.
 */
public final class CompletedAt {
    public static final String VALIDATION_REGEX = "^$|^\\d{4}-\\d{2}-\\d{2}T\\d{4}H$";
    public static final String MESSAGE_CONSTRAINTS =
            "CompletedAt must be empty or in the format YYYY-MM-DDTHHMMH.";

    private final String value;

    /**
     * Constructs a {@code CompletedAt} instance with the specified value.
     *
     * @param value The completion timestamp string.
     * @throws NullPointerException if {@code value} is null.
     * @throws IllegalArgumentException if {@code value} does not match the required format.
     */
    public CompletedAt(String value) {
        requireNonNull(value);
        String trimmedValue = value.trim();
        if (!isValidCompletedAt(trimmedValue)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = trimmedValue;
    }

    /**
     * Returns true if the given string is a valid completion timestamp.
     * A valid value is either an empty string or follows the format YYYY-MM-DDTHHMMH.
     *
     * @param test The string to validate.
     * @return True if the string satisfies the completion timestamp format.
     */
    public static boolean isValidCompletedAt(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if this completion timestamp is empty.
     *
     * @return True if no completion timestamp is recorded.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }

    /**
     * Returns the underlying string value of this completion timestamp.
     *
     * @return The completion timestamp string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if the specified object is equal to this completion timestamp.
     *
     * @param other The object to compare against.
     * @return True if {@code other} is a {@code CompletedAt} with the same value.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CompletedAt)) {
            return false;
        }
        CompletedAt otherCompletedAt = (CompletedAt) other;
        return value.equals(otherCompletedAt.value);
    }

    /**
     * Returns the hash code of this completion timestamp.
     *
     * @return The hash code based on the stored value.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns the string representation of this completion timestamp.
     *
     * @return The completion timestamp value.
     */
    @Override
    public String toString() {
        return value;
    }
}
