package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {

    public static final Assignment A_JUNIT = new Assignment(
            new AssignmentId("A1"),
            new Label("A-JUnit"),
            new Group("Sec3A"),
            new DueDate("2026-02-20"));

    public static final Assignment A_STREAMS = new Assignment(
            new AssignmentId("A2"),
            new Label("A-Streams"),
            new Group("Sec3A"),
            new DueDate("2026-02-22"));

    public static final Assignment A_REFACTOR = new Assignment(
            new AssignmentId("A3"),
            new Label("A-Refactor"),
            new Group("Sec3B"),
            new DueDate("2026-03-01"));

    public static final Assignment A_OOP = new Assignment(
            new AssignmentId("A4"),
            new Label("A-OOP"),
            new Group("Sec3B"),
            new DueDate("2026-03-08"));

    public static final Assignment A_UML = new Assignment(
            new AssignmentId("A5"),
            new Label("A-UML"),
            new Group("Sec3C"),
            new DueDate("2026-03-15"));

    private TypicalAssignments() {}

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(A_JUNIT, A_STREAMS, A_REFACTOR, A_OOP, A_UML));
    }

    public static AddressBook getTypicalAssignmentBook() {
        AddressBook ab = new AddressBook();
        for (Assignment assignment : getTypicalAssignments()) {
            ab.addAssignment(assignment);
        }
        return ab;
    }
}
