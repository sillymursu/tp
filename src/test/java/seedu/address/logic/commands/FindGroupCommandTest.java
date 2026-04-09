package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_AND_ASSIGNMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAssignments.A_CS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.GroupName;

/**
 * Contains integration tests (interaction with the Model) for {@code FindGroupCommand}.
 */
public class FindGroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindGroupCommand firstCommand = new FindGroupCommand(new GroupName("First"));
        FindGroupCommand secondCommand = new FindGroupCommand(new GroupName("Second"));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        FindGroupCommand firstCommandCopy = new FindGroupCommand(new GroupName("First"));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different name -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_wrongKeyword_noPersonAndAssignmentFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_AND_ASSIGNMENTS_LISTED_OVERVIEW, 0, 0, "L");
        GroupName searchKeyword = new GroupName("L");
        FindGroupCommand cmd = new FindGroupCommand(searchKeyword);
        expectedModel.setFilteredPersonsAndAssignmentsByGroups(searchKeyword);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_csKeyword_multiplePersonAndAssignmentFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_AND_ASSIGNMENTS_LISTED_OVERVIEW, 5, 1, "CS");
        GroupName searchKeyword = new GroupName("CS");
        FindGroupCommand cmd = new FindGroupCommand(searchKeyword);
        expectedModel.setFilteredPersonsAndAssignmentsByGroups(searchKeyword);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE), model.getFilteredPersonList());
        assertEquals(Arrays.asList(A_CS), model.getFilteredAssignmentList());
    }

    @Test
    public void toStringMethod() {
        GroupName searchKeyword = new GroupName("CS");
        FindGroupCommand cmd = new FindGroupCommand(searchKeyword);
        String expected = FindGroupCommand.class.getCanonicalName()
                + "{Group Name=" + searchKeyword + "}";
        assertEquals(expected, cmd.toString());
    }
}
