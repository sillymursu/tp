package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the label for an Assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidLabel(String)}.
 */
public class Label {

    public static final String MESSAGE_CONSTRAINTS = "Label can take any ASCII values, "
            + "and it should not be blank";

    /*
     * The first character of the label must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\x21-\\x7E][\\x20-\\x7E]*$";

    public final String label;

    /**
     * Constructs a {@code Label}.
     *
     * @param label A valid label.
     */
    public Label(String label) {
        requireNonNull(label);
        checkArgument(isValidLabel(label), MESSAGE_CONSTRAINTS);
        this.label = label;
    }

    /**
     * Returns true if a given string is a valid label.
     *
     * @param test The string to validate.
     * @return {@code true} if {@code test} is a valid label, {@code false} otherwise.
     */
    public static boolean isValidLabel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of this label.
     *
     * @return The label as a string.
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Returns true if both labels have the same value.
     *
     * @param other The object to compare against.
     * @return {@code true} if this label is equal to the other object, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Label)) {
            return false;
        }

        Label otherLabel = (Label) other;
        return label.equals(otherLabel.label);
    }

    /**
     * Returns the hash code value of this label.
     *
     * @return The hash code of this label.
     */
    @Override
    public int hashCode() {
        return label.hashCode();
    }
}

