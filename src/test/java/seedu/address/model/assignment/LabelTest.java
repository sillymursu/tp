package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LabelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Label(null));
    }

    @Test
    public void constructor_invalidLabel_throwsIllegalArgumentException() {
        String invalidLabel = "";
        assertThrows(IllegalArgumentException.class, () -> new Label(invalidLabel));
    }

    @Test
    public void isValidLabel() {
        // null label
        assertThrows(NullPointerException.class, () -> Label.isValidLabel(null));

        // invalid labels
        assertFalse(Label.isValidLabel("")); // empty string
        assertFalse(Label.isValidLabel(" ")); // single whitespace
        assertFalse(Label.isValidLabel("   ")); // multiple whitespaces
        assertFalse(Label.isValidLabel(" A-JUnit")); // leading whitespace
        assertFalse(Label.isValidLabel("\tA-JUnit")); // leading tab

        // valid labels
        assertTrue(Label.isValidLabel("A-JUnit"));
        assertTrue(Label.isValidLabel("A 1"));
        assertTrue(Label.isValidLabel("A/JUnit"));
        assertTrue(Label.isValidLabel("A-JUnit ")); // trailing whitespace allowed by regex
        assertTrue(Label.isValidLabel("@work")); // non-whitespace starting character
    }

    @Test
    public void equals() {
        Label label = new Label("A-JUnit");

        // same values -> returns true
        assertTrue(label.equals(new Label("A-JUnit")));

        // same object -> returns true
        assertTrue(label.equals(label));

        // null -> returns false
        assertFalse(label.equals(null));

        // different types -> returns false
        assertFalse(label.equals(5.0f));

        // different values -> returns false
        assertFalse(label.equals(new Label("A-Streams")));
    }
}
