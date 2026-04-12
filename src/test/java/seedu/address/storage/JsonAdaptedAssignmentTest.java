package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAssignment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.A_TEST;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;

public class JsonAdaptedAssignmentTest {

    private static final String INVALID_ASSIGNMENT_ID = "X1";
    private static final String INVALID_LABEL = "";
    private static final String INVALID_DUE_DATE = "2026-13-01";
    private static final String INVALID_GROUP = "";

    private static final String VALID_ASSIGNMENT_ID = A_TEST.getAssignmentId().getValue();
    private static final String VALID_LABEL = A_TEST.getLabel().toString();
    private static final String VALID_DUE_DATE = A_TEST.getDueDate().toStorageString();
    private static final List<String> VALID_GROUPS = A_TEST.getGroups().stream()
            .map(g -> g.getGroupName().toString())
            .toList();

    @Test
    public void toModelType_validAssignmentDetails_returnsAssignment() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(A_TEST);
        assertEquals(A_TEST, assignment.toModelType());
    }

    @Test
    public void toModelType_invalidAssignmentId_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(
                INVALID_ASSIGNMENT_ID, VALID_LABEL, VALID_GROUPS, VALID_DUE_DATE);
        String expectedMessage = AssignmentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullAssignmentId_returnsAssignmentWithFallbackId() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(
                null, VALID_LABEL, VALID_GROUPS, VALID_DUE_DATE);
        assertEquals(new AssignmentId("A0"), assignment.toModelType().getAssignmentId());
    }

    @Test
    public void toModelType_invalidLabel_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(
                VALID_ASSIGNMENT_ID, INVALID_LABEL, VALID_GROUPS, VALID_DUE_DATE);
        String expectedMessage = Label.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullLabel_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(
                VALID_ASSIGNMENT_ID, null, VALID_GROUPS, VALID_DUE_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullDueDate_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(
                VALID_ASSIGNMENT_ID, VALID_LABEL, VALID_GROUPS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DueDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_emptyGroups_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(
                VALID_ASSIGNMENT_ID, VALID_LABEL, new ArrayList<>(), VALID_DUE_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "groups");
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidGroups_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(
                VALID_ASSIGNMENT_ID, VALID_LABEL, List.of(INVALID_GROUP), VALID_DUE_DATE);
        String expectedMessage = Group.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }
}
