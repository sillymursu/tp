package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class GetStudentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new GetStudentsCommand(), model, GetStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new GetStudentsCommand(), model, GetStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_showsAllStudents() {
        // Start from an empty visible list to prove command resets the filter to show-all
        model.updateFilteredPersonList(a -> false);
        assertTrue(model.getFilteredPersonList().isEmpty());

        GetStudentsCommand command = new GetStudentsCommand();

        assertCommandSuccess(command, model, GetStudentsCommand.MESSAGE_SUCCESS, expectedModel);

        // Post-condition check: filtered list should now show all students
        assertEquals(model.getAddressBook().getPersonList().size(),
                model.getFilteredPersonList().size());
    }

    @Test
    public void execute_emptyAddressBook_returnsNoStudentsFound() {
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        Model expectedEmptyModel = new ModelManager(emptyModel.getAddressBook(), new UserPrefs());

        GetStudentsCommand command = new GetStudentsCommand();

        assertCommandSuccess(command, emptyModel, GetStudentsCommand.MESSAGE_NO_STUDENTS, expectedEmptyModel);
        assertTrue(emptyModel.getFilteredPersonList().isEmpty());
    }

    @Test
    public void equals() {
        GetStudentsCommand firstCommand = new GetStudentsCommand();
        GetStudentsCommand secondCommand = new GetStudentsCommand();

        // same object -> returns true
        assertEquals(firstCommand, firstCommand);

        // same values -> returns true
        assertEquals(firstCommand, secondCommand);

        // different types -> returns false
        assertNotEquals(1, firstCommand);

        // null -> returns false
        assertNotEquals(null, firstCommand);
    }
}
