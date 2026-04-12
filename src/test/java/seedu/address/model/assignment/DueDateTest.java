package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DueDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DueDate(null));
    }

    @Test
    public void constructor_invalidDueDate_throwsIllegalArgumentException() {
        String invalidDueDate = "20-04-2026";
        assertThrows(IllegalArgumentException.class, () -> new DueDate(invalidDueDate));
    }

    @Test
    public void isValidDate() {
        // null due date
        assertThrows(NullPointerException.class, () -> DueDate.isValidDateFormat(null));

        // invalid due dates
        assertFalse(DueDate.isValidDateFormat("20-04-2026"));
        assertFalse(DueDate.isValidDateFormat("2026/04/20"));
        assertFalse(DueDate.isValidDate("2026-13-20"));
        assertFalse(DueDate.isValidDateFormat("abcd-ef-gh"));
        assertFalse(DueDate.isValidDateFormat(""));
        assertFalse(DueDate.isValidDateFormat("   "));
        assertFalse(DueDate.isValidDate("2026-02-31"));
        assertFalse(DueDate.isValidDate("2026-04-32"));

        // valid due dates
        assertTrue(DueDate.isValidDate("2026-04-20"));
        assertTrue(DueDate.isValidDate("1999-12-31"));
        // leap year
        assertTrue(DueDate.isValidDate("2028-02-29"));
    }

    @Test
    public void equals() {
        DueDate dueDate = new DueDate("2026-04-20");

        // same values -> returns true
        assertTrue(dueDate.equals(new DueDate("2026-04-20")));

        // same object -> returns true
        assertTrue(dueDate.equals(dueDate));

        // null -> returns false
        assertFalse(dueDate.equals(null));

        // different types -> returns false
        assertFalse(dueDate.equals(5.0f));

        // different values -> returns false
        assertFalse(dueDate.equals(new DueDate("2026-04-21")));
    }
}
