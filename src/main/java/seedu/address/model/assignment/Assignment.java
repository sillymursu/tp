package seedu.address.model.assignment;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;



/**
 * Represents an Assignment in LeTutor
 * Guarantees: details are present, field values are validated, immutable.
 */
public class Assignment {
    // Assignment fields
    private final Label label;
    private final Group group;
    private final DueDate dueDate;
    private final Order order;

    /**
     * Every field must be present
     */
    public Assignment(Label label, Group group, DueDate dueDate, Order order) {
        this.label = label;
        this.group = group;
        this.dueDate = dueDate;
        this.order = order;
    }

    public Label getLabel() {
        return label;
    }

    public Group getGroup() {
        return group;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public Order getOrder() {
        return order;
    }

    /**
     * Returns true if both Assignments have the same fields
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return label.equals(otherAssignment.getLabel())
                && group.equals(otherAssignment.getGroup())
                && dueDate.equals(otherAssignment.getDueDate())
                && order.equals(otherAssignment.getOrder());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(label, group, dueDate, order);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("label", label)
                .add("group", group)
                .add("due date", dueDate)
                .add("order", order)
                .toString();
    }
}
