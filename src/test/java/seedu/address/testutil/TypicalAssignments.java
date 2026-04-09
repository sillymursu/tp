package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DUEDATE_A_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DUEDATE_B_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_A_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_B_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_A_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_B_TEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.assignment.Assignment;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {

    public static final Assignment A_JUNIT = new AssignmentBuilder()
            .withAssignmentId("A1")
            .withLabel("A-JUnit")
            .withGroups("Sec3A")
            .withDueDate("2026-02-20")
            .build();

    public static final Assignment A_STREAMS = new AssignmentBuilder()
            .withAssignmentId("A2")
            .withLabel("A-Streams")
            .withGroups("Sec3A")
            .withDueDate("2026-02-22")
            .build();

    public static final Assignment A_REFACTOR = new AssignmentBuilder()
            .withAssignmentId("A3")
            .withLabel("A-Refactor")
            .withGroups("Sec3B")
            .withDueDate("2026-03-01")
            .build();

    public static final Assignment A_OOP = new AssignmentBuilder()
            .withAssignmentId("A4")
            .withLabel("A-OOP")
            .withGroups("Sec3B")
            .withDueDate("2026-03-08")
            .build();

    public static final Assignment A_UML = new AssignmentBuilder()
            .withAssignmentId("A5")
            .withLabel("A-UML")
            .withGroups("Sec3C")
            .withDueDate("2026-03-15")
            .build();

    public static final Assignment A_CS = new AssignmentBuilder()
            .withAssignmentId("A6")
            .withLabel("A-CS")
            .withGroups("CS")
            .withDueDate("2026-03-16")
            .build();

    public static final Assignment A_TEST = new AssignmentBuilder().withAssignmentId("A6").withLabel(VALID_LABEL_A_TEST)
            .withGroups(VALID_GROUP_A_TEST).withDueDate(VALID_DUEDATE_A_TEST).build();

    public static final Assignment B_TEST = new AssignmentBuilder().withAssignmentId("A7").withLabel(VALID_LABEL_B_TEST)
            .withGroups(VALID_GROUP_B_TEST).withDueDate(VALID_DUEDATE_B_TEST).build();

    private TypicalAssignments() {}

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(A_JUNIT, A_STREAMS, A_REFACTOR, A_OOP, A_UML, A_CS));
    }

    public static AddressBook getTypicalAssignmentBook() {
        AddressBook ab = new AddressBook();
        for (Assignment assignment : getTypicalAssignments()) {
            ab.addAssignment(assignment);
        }
        return ab;
    }
}
