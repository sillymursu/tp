package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignmentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignmentId(null));
    }

    @Test
    public void constructor_invalidAssignmentId_throwsIllegalArgumentException() {
        String invalidAssignmentId = "B123";
        assertThrows(IllegalArgumentException.class, () -> new AssignmentId(invalidAssignmentId));
    }

    @Test
    public void isValidAssignmentId() {
        // null assignment id
        assertFalse(AssignmentId.isValidAssignmentId(null));

        // invalid assignment ids
        assertFalse(AssignmentId.isValidAssignmentId("")); // empty string
        assertFalse(AssignmentId.isValidAssignmentId("A")); // missing digits
        assertFalse(AssignmentId.isValidAssignmentId("A12345")); // more than 4 digits
        assertFalse(AssignmentId.isValidAssignmentId("B123")); // wrong prefix
        assertFalse(AssignmentId.isValidAssignmentId("A12B")); // non-digit in numeric part

        // valid assignment ids
        assertTrue(AssignmentId.isValidAssignmentId("A1"));
        assertTrue(AssignmentId.isValidAssignmentId("A301"));
        assertTrue(AssignmentId.isValidAssignmentId("A9999"));
        assertTrue(AssignmentId.isValidAssignmentId("  A123  ")); // trimmed before validation
    }

    @Test
    public void equals() {
        AssignmentId assignmentId = new AssignmentId("A301");

        // same values -> returns true
        assertTrue(assignmentId.equals(new AssignmentId("A301")));

        // same object -> returns true
        assertTrue(assignmentId.equals(assignmentId));

        // null -> returns false
        assertFalse(assignmentId.equals(null));

        // different types -> returns false
        assertFalse(assignmentId.equals(5.0f));

        // different values -> returns false
        assertFalse(assignmentId.equals(new AssignmentId("A302")));
    }
}
