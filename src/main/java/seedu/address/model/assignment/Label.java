package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the label for an Assignment
 * Guarantees: immutable; is valid as declared in {@link #isValidLabel(String)}
 */
public class Label {

    public static final String MESSAGE_CONSTRAINTS = "Label can take any values, and it should not be blank";

    /*
     * The first character of the label must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String label;

    /**
     * Constructs a {@code Label}.
     *
     * @params label A valid label.
     */
    public Label(String label) {
        requireNonNull(label);
        checkArgument(isValidLabel(label), MESSAGE_CONSTRAINTS);
        this.label = label;
    }

    /**
     * Returns true if a given string is a valid label.
     */
    public static boolean isValidLabel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return label;
    }

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

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
