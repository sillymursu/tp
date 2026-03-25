package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENTS;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Group;
import seedu.address.model.assignment.Label;

/**
 * Edits the details if an existing assignment in the assignment library
 */
public class EditAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " /assignment: Edits the details of an assignment \n"
            + "Format: edit /assignment <assignmentId> {<label>; <group>; <dueDate>} \n"
            + "Example: edit /assignment A1 {A-Testing; Sec3A; 2026-04-30}";

    public static final String MESSAGE_EDIT_ASSIGNMENT_SUCCESS = "Edited Assignment: %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the address book.";
    public static final String MESSAGE_NOT_FOUND = "Assignment not found: %s";

    private final AssignmentId assignmentId;
    private final EditAssignmentDescriptor editAssignmentDescriptor;

    /**
     * Creates an EditAssignmentCommand to edit the specified {@code Assignment}
     *
     * @param assignmentId of the assignment to edit
     * @param editAssignmentDescriptor details to edit the assignment with
     */
    public EditAssignmentCommand(AssignmentId assignmentId, EditAssignmentDescriptor editAssignmentDescriptor) {
        requireNonNull(assignmentId);
        requireNonNull(editAssignmentDescriptor);
        this.assignmentId = assignmentId;
        this.editAssignmentDescriptor = editAssignmentDescriptor;
    }

    /**
     * Executes the edit assignment command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} containing the success message and edited assignment details.
     * @throws CommandException If no assignment with the given assignmentId exists, or if the edited assignment
     *         is a duplicate of an existing assignment.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Assignment> maybe = model.getAssignmentById(assignmentId);

        if (maybe.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NOT_FOUND, assignmentId));
        }

        Assignment assignmentToEdit = maybe.get();
        Assignment editedAssignment = createEditedAssignment(assignmentToEdit, editAssignmentDescriptor);

        // Excludes the current assignment being edited from the duplicate check
        boolean duplicate = model.getAssignmentList().stream()
                .filter(a -> !a.getAssignmentId().equals(assignmentToEdit.getAssignmentId()))
                .anyMatch(a ->
                        a.getLabel().equals(editedAssignment.getLabel())
                                && a.getGroup().equals(editedAssignment.getGroup())
                                && a.getDueDate().equals(editedAssignment.getDueDate())
        );

        if (duplicate) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.setAssignment(assignmentToEdit, editedAssignment);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToEdit}
     * edited with {@code editAssignmentDescriptor}.
     */
    private static Assignment createEditedAssignment(Assignment assignmentToEdit,
                                                     EditAssignmentDescriptor editAssignmentDescriptor) {
        assert assignmentToEdit != null;

        Label updatedLabel = editAssignmentDescriptor.getLabel().orElse(assignmentToEdit.getLabel());
        Group updatedGroup = editAssignmentDescriptor.getGroup().orElse(assignmentToEdit.getGroup());
        DueDate updatedDueDate = editAssignmentDescriptor.getDueDate().orElse(assignmentToEdit.getDueDate());

        return new Assignment(
                assignmentToEdit.getAssignmentId(),
                updatedLabel,
                updatedGroup,
                updatedDueDate
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditAssignmentCommand)) {
            return false;
        }

        EditAssignmentCommand e = (EditAssignmentCommand) other;
        return assignmentId.equals(e.assignmentId)
                && editAssignmentDescriptor.equals(e.editAssignmentDescriptor);
    }

    /**
     * Stores the details to edit the assignment with. Each non-empty field value will replace the
     * corresponding field value of the assignment.
     */
    public static class EditAssignmentDescriptor {
        private Label label;
        private Group group;
        private DueDate dueDate;

        public EditAssignmentDescriptor() {}

        /**
         * Copy constructor.
         * @param toCopy The {@code EditAssignmentDescriptor} to copy.
         */
        public EditAssignmentDescriptor(EditAssignmentDescriptor toCopy) {
            setLabel(toCopy.label);
            setGroup(toCopy.group);
            setDueDate(toCopy.dueDate);
        }

        /**
         * Returns true if at least one field is edited
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(label, group, dueDate);
        }

        public void setLabel(Label label) {
            this.label = label;
        }

        public Optional<Label> getLabel() {
            return Optional.ofNullable(label);
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

        public void setDueDate(DueDate dueDate) {
            this.dueDate = dueDate;
        }

        public Optional<DueDate> getDueDate() {
            return Optional.ofNullable(dueDate);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditAssignmentDescriptor)) {
                return false;
            }

            EditAssignmentDescriptor e = (EditAssignmentDescriptor) other;
            return getLabel().equals(e.getLabel())
                    && getGroup().equals(e.getGroup())
                    && getDueDate().equals(e.getDueDate());
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("label", label)
                    .add("group", group)
                    .add("dueDate", dueDate)
                    .toString();
        }
    }
}


