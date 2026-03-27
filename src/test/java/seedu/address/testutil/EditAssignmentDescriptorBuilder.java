package seedu.address.testutil;

import seedu.address.logic.commands.EditAssignmentCommand.EditAssignmentDescriptor;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;

/**
 * A utility class to help with building EditAssignmentDescriptor objects.
 */
public class EditAssignmentDescriptorBuilder {

    private EditAssignmentDescriptor descriptor;

    public EditAssignmentDescriptorBuilder() {
        descriptor = new EditAssignmentDescriptor();
    }

    public EditAssignmentDescriptorBuilder(EditAssignmentDescriptor descriptor) {
        this.descriptor = new EditAssignmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAssignmentDescriptor} with fields containing {@code assignment}'s details
     */
    public EditAssignmentDescriptorBuilder(Assignment assignment) {
        descriptor = new EditAssignmentDescriptor();
        descriptor.setLabel(assignment.getLabel());
        descriptor.setGroup(assignment.getGroup());
        descriptor.setDueDate(assignment.getDueDate());
    }

    /**
     * Sets the {@code Label} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withLabel(String label) {
        descriptor.setLabel(new Label(label));
        return this;
    }

    /**
     * Sets the {@code Group} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withGroup (String group) {
        descriptor.setGroup(new Group(group));
        return this;
    }

    /**
     * Sets the {@code DueDate} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withDueDate(String dueDate) {
        descriptor.setDueDate(new DueDate(dueDate));
        return this;
    }

    public EditAssignmentDescriptor build() {
        return descriptor;
    }
}
