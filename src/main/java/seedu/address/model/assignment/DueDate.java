package seedu.address.model.assignment;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents the due date for an Assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateFormat(String)}.
 */
public class DueDate {

    public static final String MESSAGE_CONSTRAINTS = "DueDate can only be in the format YYYY-MM-DD "
            + "and should only contain digits";

    public static final String MESSAGE_CONSTRAINTS_DATE = "DueDate cannot be an invalid date";

    public final LocalDate date;

    /**
     * Constructs a {@code DueDate}.
     *
     * @param date A valid date string.
     */
    public DueDate(String date) {
        checkArgument(isValidDateFormat(date), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS_DATE);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is of valid date format
     *
     * @param date The string to validate.
     * @return {@code true} if {@code date} follows the format {@code yyyy-MM-dd},
     *         {@code false} otherwise.
     */
    public static boolean isValidDateFormat(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param date The string to validate.
     * @return {@code true} if {@code date} is a valid date (29 during leap year),
     *         {@code false} otherwise.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT));
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
