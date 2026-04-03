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

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GetAssignmentCommand
 */
public class GetAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());

    @Test
    public void execute_validAssignmentId_success() throws CommandException {
        Assignment assignmentToGet = A_JUNIT;
        AssignmentId targetAssignmentId = assignmentToGet.getAssignmentId();
        GetAssignmentCommand getAssignmentCommand = new GetAssignmentCommand(targetAssignmentId);

        String expectedMessage = String.format(GetAssignmentCommand.MESSAGE_GET_ASSIGNMENT_SUCCESS, targetAssignmentId);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(getAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAssignmentId_throwsCommandException() {
        AssignmentId invalidAssignmentId = new AssignmentId("A999");
        GetAssignmentCommand getAssignmentCommand = new GetAssignmentCommand(invalidAssignmentId);

        String expectedMessage = String.format(GetAssignmentCommand.MESSAGE_NOT_FOUND, invalidAssignmentId);
        assertCommandFailure(getAssignmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_validAssignmentIdFilteredList_success() {
        showNoAssignment(model);

        Assignment assignmentToGet = A_JUNIT;
        AssignmentId targetAssignmentId = assignmentToGet.getAssignmentId();
        GetAssignmentCommand getAssignmentCommand = new GetAssignmentCommand(targetAssignmentId);

        String expectedMessage = String.format(GetAssignmentCommand.MESSAGE_GET_ASSIGNMENT_SUCCESS, targetAssignmentId);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(getAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        GetAssignmentCommand getFirstCommand = new GetAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT);
        GetAssignmentCommand getSecondCommand = new GetAssignmentCommand(ASSIGNMENT_ID_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(getFirstCommand.equals(getFirstCommand));

        // same values -> returns true
        GetAssignmentCommand getFirstCommandCopy = new GetAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT);
        assertTrue(getFirstCommand.equals(getFirstCommandCopy));

        // different types -> returns false
        assertFalse(getFirstCommand.equals(1));

        // null -> returns false
        assertFalse(getFirstCommand.equals(null));

        // different assignment id -> returns false
        assertFalse(getFirstCommand.equals(getSecondCommand));
    }
}
