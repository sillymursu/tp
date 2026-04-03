package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalAssignmentBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GetAssignmentsCommand
 */
public class GetAssignmentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_nonEmptyAssignmentBook_showsAllAssignments() {
        // Start from an empty visible list to prove command resets the filter to show-all
        model.updateFilteredAssignmentList(a -> false);
        assertTrue(model.getFilteredAssignmentList().isEmpty());

        GetAssignmentsCommand command = new GetAssignmentsCommand();

        assertCommandSuccess(command, model, GetAssignmentsCommand.MESSAGE_SUCCESS, expectedModel);

        // Post-condition check: filtered list should now show all assignments
        assertEquals(model.getAddressBook().getAssignmentList().size(),
                model.getFilteredAssignmentList().size());
    }

    @Test
    public void execute_emptyAssignmentBook_returnsNoAssignmentsFound() {
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        Model expectedEmptyModel = new ModelManager(emptyModel.getAddressBook(), new UserPrefs());

        GetAssignmentsCommand command = new GetAssignmentsCommand();

        assertCommandSuccess(command, emptyModel, GetAssignmentsCommand.MESSAGE_NO_ASSIGNMENTS, expectedEmptyModel);
        assertTrue(emptyModel.getFilteredAssignmentList().isEmpty());
    }

    @Test
    public void equals() {
        GetAssignmentsCommand firstCommand = new GetAssignmentsCommand();
        GetAssignmentsCommand secondCommand = new GetAssignmentsCommand();

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        assertTrue(firstCommand.equals(secondCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));
    }
}
