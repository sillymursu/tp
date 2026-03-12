package seedu.address.model.assignment;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the due date for an Assignment
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DueDate {

    public static final String MESSAGE_CONSTRAINTS = "DueDate can only be in the format YYYY-MM-DD, or blank";

    public final LocalDate date;

    /**
     * Constructs a {@code DueDate}.
     *
     * @params date A valid date.
     */
    public DueDate(String date) {
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
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

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

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

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
