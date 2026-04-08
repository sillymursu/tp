package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showNoAssignment;
import static seedu.address.testutil.TypicalAssignmentId.ASSIGNMENT_ID_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalAssignmentId.ASSIGNMENT_ID_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalAssignments.getTypicalAssignmentBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditAssignmentCommand.EditAssignmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.EditAssignmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditAssignmentCommand}.
 */
public class EditAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Assignment assignmentToEdit = model.getAssignmentList().get(0);
        Assignment editedAssignment = new AssignmentBuilder(assignmentToEdit)
                .withLabel("A-Edited")
                .withGroup("Sec4A")
                .withDueDate("2026-05-01")
                .build();

        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(assignmentToEdit.getAssignmentId(),
                descriptor);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS,
                Messages.formatA(editedAssignment));
        Model expectedModel = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());
        expectedModel.setAssignment(assignmentToEdit, editedAssignment);

        assertCommandSuccess(editAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Assignment assignmentToEdit = model.getAssignmentList().get(0);
        Assignment editedAssignment = new AssignmentBuilder(assignmentToEdit)
                .withLabel("A-PartialEdit")
                .build();

        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(assignmentToEdit.getAssignmentId(),
                descriptor);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS,
                Messages.formatA(editedAssignment));
        Model expectedModel = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());
        expectedModel.setAssignment(assignmentToEdit, editedAssignment);

        assertCommandSuccess(editAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Assignment assignmentToEdit = model.getAssignmentList().get(0);
        Assignment editedAssignment = new AssignmentBuilder(assignmentToEdit).build();

        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(assignmentToEdit.getAssignmentId(),
                descriptor);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS,
                Messages.formatA(editedAssignment));
        Model expectedModel = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());
        expectedModel.setAssignment(assignmentToEdit, editedAssignment);

        assertCommandSuccess(editAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showNoAssignment(model);

        Assignment assignmentToEdit = model.getAssignmentList().get(0);
        Assignment editedAssignment = new AssignmentBuilder(assignmentToEdit)
                .withLabel("A-PartialEdit")
                .build();

        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(assignmentToEdit.getAssignmentId(),
                descriptor);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS,
                Messages.formatA(editedAssignment));
        Model expectedModel = new ModelManager(getTypicalAssignmentBook(), new UserPrefs());
        expectedModel.setAssignment(assignmentToEdit, editedAssignment);

        assertCommandSuccess(editAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAssignmentUnfilteredList_failure() {
        Assignment first = model.getAssignmentById(ASSIGNMENT_ID_FIRST_ASSIGNMENT).orElseThrow();
        Assignment second = model.getAssignmentById(ASSIGNMENT_ID_SECOND_ASSIGNMENT).orElseThrow();

        // Try editing second assignment into first assignment's fields -> duplicate
        EditAssignmentDescriptor descriptor =
                new EditAssignmentDescriptorBuilder(first).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(second.getAssignmentId(), descriptor);

        assertCommandFailure(editAssignmentCommand, model, EditAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }

    @Test
    public void execute_duplicateAssignmentFilteredList_failure() {
        showNoAssignment(model);

        Assignment first = model.getAssignmentById(ASSIGNMENT_ID_FIRST_ASSIGNMENT).orElseThrow();
        Assignment second = model.getAssignmentById(ASSIGNMENT_ID_SECOND_ASSIGNMENT).orElseThrow();

        // Try editing second assignment into first assignment's fields -> duplicate
        EditAssignmentDescriptor descriptor =
                new EditAssignmentDescriptorBuilder(first).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(second.getAssignmentId(), descriptor);

        assertCommandFailure(editAssignmentCommand, model, EditAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }

    @Test
    public void execute_invalidAssignmentId_failure() {
        AssignmentId invalidAssignmentId = new AssignmentId("A999");
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder().withLabel("A-New").build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(invalidAssignmentId, descriptor);

        assertCommandFailure(editAssignmentCommand, model,
                String.format(EditAssignmentCommand.MESSAGE_NOT_FOUND, invalidAssignmentId));
    }

    @Test
    public void equals() {
        EditAssignmentDescriptor firstDescriptor = new EditAssignmentDescriptorBuilder()
                .withLabel("A-Edited1").build();
        EditAssignmentDescriptor secondDescriptor = new EditAssignmentDescriptorBuilder()
                .withLabel("A-Edited2").build();

        EditAssignmentCommand firstCommand = new EditAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT,
                firstDescriptor);
        EditAssignmentCommand secondCommand = new EditAssignmentCommand(ASSIGNMENT_ID_SECOND_ASSIGNMENT,
                secondDescriptor);

        // same values -> returns true
        EditAssignmentCommand firstCommandCopy =
                new EditAssignmentCommand(ASSIGNMENT_ID_FIRST_ASSIGNMENT,
                        new EditAssignmentCommand.EditAssignmentDescriptor(firstDescriptor));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different types -> returns false
        assertFalse(firstCommand.equals(new ClearCommand()));

        // different values -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
