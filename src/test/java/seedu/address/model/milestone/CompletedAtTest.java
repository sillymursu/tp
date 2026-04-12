package seedu.address.model.milestone;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompletedAtTest {

    @Test
    public void isValidCompletedAt() {
        assertFalse(CompletedAt.isValidCompletedAt(null)); // null
        assertTrue(CompletedAt.isValidCompletedAt("")); // blank allowed
        assertFalse(CompletedAt.isValidCompletedAt(" ")); // whitespace only
        assertFalse(CompletedAt.isValidCompletedAt("2026-03-30")); // missing time
        assertFalse(CompletedAt.isValidCompletedAt("30-03-2026T1200H")); // wrong date format
        assertFalse(CompletedAt.isValidCompletedAt("2026-05-32T0900H")); // impossible date
        assertFalse(CompletedAt.isValidCompletedAt("2026-03-30T2460H")); // impossible time
        assertTrue(CompletedAt.isValidCompletedAt("2026-03-30T1200H")); // valid
        assertTrue(CompletedAt.isValidCompletedAt("2025-12-01T2359H")); // valid
    }

    @Test
    public void equals() {
        CompletedAt completedAt = new CompletedAt("2026-03-30T1200H");
        CompletedAt sameCompletedAt = new CompletedAt("2026-03-30T1200H");
        CompletedAt differentCompletedAt = new CompletedAt("");

        assertTrue(completedAt.equals(completedAt));
        assertTrue(completedAt.equals(sameCompletedAt));

        assertFalse(completedAt.equals(null));
        assertFalse(completedAt.equals(5));
        assertFalse(completedAt.equals(differentCompletedAt));
    }
}
