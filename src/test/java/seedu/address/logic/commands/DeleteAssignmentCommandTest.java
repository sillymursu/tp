package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showNoAssignment;
import static seedu.address.testutil.TypicalAssignmentId.ASSIGNMENT_ID_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalAssignmentId.ASSIGNMENT_ID_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalAssignments.A_JUNIT;
import static seedu.address.testutil.TypicalAssignments.getTypicalAssignmentBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAssignmentCommand}.
 */
public class DeleteAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());

    @Test
    public void execute_validAssignmentIdUnfilteredList_success() {
        Assignment assignmentToDelete = A_JUNIT;
        AssignmentId targetAssignmentId = assignmentToDelete.getAssignmentId();
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(targetAssignmentId);

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS,
                targetAssignmentId);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAssignment(assignmentToDelete);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAssignmentIdUnfilteredList_throwsCommandException() {
        AssignmentId missingAssignmentId = new AssignmentId("A999");
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(missingAssignmentId);

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_NOT_FOUND, missingAssignmentId);
        assertCommandFailure(deleteAssignmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_validAssignmentIdFilteredList_success() {
        showNoAssignment(model);

        Assignment assignmentToDelete = A_JUNIT;
        AssignmentId targetAssignmentId = assignmentToDelete.getAssignmentId();
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(targetAssignmentId);

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS,
                targetAssignmentId);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAssignment(assignmentToDelete);
        showNoAssignment(expectedModel);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAssignmentIdFilteredList_throwsCommandException() {
        showNoAssignment(model);

        AssignmentId missingAssignmentId = new AssignmentId("A999");
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(missingAssignmentId);
        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_NOT_FOUND, missingAssignmentId);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAssignment(A_JUNIT);
        showNoAssignment(expectedModel);

        assertCommandFailure(deleteAssignmentCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteAssignmentCommand deleteFirstCommand = new DeleteAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT);
        DeleteAssignmentCommand deleteSecondCommand = new DeleteAssignmentCommand(ASSIGNMENT_ID_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAssignmentCommand deleteFirstCommandCopy = new DeleteAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
