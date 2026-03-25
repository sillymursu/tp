package seedu.address.model.assignment;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;

/**
 * Represents an Assignment in LeTutor.
 * Guarantees: details are present, field values are validated, immutable.
 */
public class Assignment {

    private final AssignmentId assignmentId;
    private final Label label;
    private final Group group;
    private final DueDate dueDate;

    /**
     * Creates an {@code Assignment} with the given identifier, label, group, and due date.
     * Every field must be present.
     *
     * @param assignmentId Unique identifier for the assignment (e.g., A301).
     * @param label The label shown to users (e.g., A-JUnit).
     * @param group Group/class tag (stored as plain String). Use "" if not applicable.
     * @param dueDate Due date of assignment (can be "" depending on DueDate rules).
     */
    public Assignment(AssignmentId assignmentId, Label label, Group group, DueDate dueDate) {
        this.assignmentId = assignmentId;
        this.label = label;
        this.group = group;
        this.dueDate = dueDate;
    }

    /**
     * Returns the unique identifier of this assignment.
     *
     * @return The assignment identifier.
     */
    public AssignmentId getAssignmentId() {
        return assignmentId;
    }

    /**
     * Returns the label of this assignment.
     *
     * @return The assignment label.
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Returns the group or class tag associated with this assignment.
     *
     * @return The group string.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Returns the due date of this assignment.
     *
     * @return The assignment due date.
     */
    public DueDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns true if both Assignments have the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return assignmentId.equals(otherAssignment.assignmentId)
                && label.equals(otherAssignment.label)
                && group.equals(otherAssignment.group)
                && dueDate.equals(otherAssignment.dueDate);
    }

    /**
     * Returns true if both Assignments have the same label and group.
     * This defines a weaker notion of equality between two Assignments.
     *
     * @param otherAssignment The assignment to compare against.
     * @return {@code true} if the assignments have the same label and group, {@code false} otherwise.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }
        return otherAssignment != null
                && otherAssignment.getLabel().equals(getLabel())
                && otherAssignment.getGroup().equals(getGroup());
    }

    /**
     * Returns the hash code value of this assignment.
     *
     * @return The hash code of this assignment.
     */
    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, label, group, dueDate);
    }

    /**
     * Returns a string representation of this assignment.
     *
     * @return A string representation of this assignment.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("assignmentId", assignmentId)
                .add("label", label)
                .add("group", group)
                .add("dueDate", dueDate)
                .toString();
    }
}
