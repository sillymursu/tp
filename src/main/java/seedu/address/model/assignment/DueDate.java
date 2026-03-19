package seedu.address.model.assignment;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the due date for an Assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}.
 */
public class DueDate {

    public static final String MESSAGE_CONSTRAINTS = "DueDate can only be in the format YYYY-MM-DD, or blank";

    public final LocalDate date;

    /**
     * Constructs a {@code DueDate}.
     *
     * @param date A valid date string.
     */
    public DueDate(String date) {
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param date The string to validate.
     * @return {@code true} if {@code date} is blank or follows the format {@code yyyy-MM-dd},
     *         {@code false} otherwise.
     */
    public static boolean isValidDate(String date) {
        if (date.trim().isEmpty()) {
            return true;
        }

        try {
            LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Converts this due date into a storage string in the format {@code yyyy-MM-dd}.
     *
     * @return The storage string in the correct format.
     */
    public String toStorageString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation of this due date for display.
     *
     * @return The formatted due date string.
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns true if both due dates have the same value.
     *
     * @param other The object to compare against.
     * @return {@code true} if this due date is equal to the other object, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DueDate)) {
            return false;
        }

        DueDate otherDueDate = (DueDate) other;
        return date.equals(otherDueDate.date);
    }

    /**
     * Returns the hash code value of this due date.
     *
     * @return The hash code of this due date.
     */
    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
