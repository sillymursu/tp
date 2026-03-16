package seedu.address.model.assignment;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Assignment in LeTutor.
 * Guarantees: details are present, field values are validated, immutable.
 */
public class Assignment {

    private final AssignmentId assignmentId;
    private final Label label;
    private final String group;
    private final DueDate dueDate;

    /**
     * Every field must be present.
     *
     * @param assignmentId Unique identifier for the assignment (e.g., A301).
     * @param label The label shown to users (e.g., A-JUnit).
     * @param group Group/class tag (stored as plain String). Use "" if not applicable.
     * @param dueDate Due date of assignment (can be "" depending on DueDate rules).
     */
    public Assignment(AssignmentId assignmentId, Label label, String group, DueDate dueDate) {
        this.assignmentId = assignmentId;
        this.label = label;
        this.group = group == null ? "" : group.trim();
        this.dueDate = dueDate;
    }

    public AssignmentId getAssignmentId() {
        return assignmentId;
    }

    public Label getLabel() {
        return label;
    }

    public String getGroup() {
        return group;
    }

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
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }
        return otherAssignment != null
                && otherAssignment.getLabel().equals(getLabel())
                && otherAssignment.getGroup().equals(getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, label, group, dueDate);
    }

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
