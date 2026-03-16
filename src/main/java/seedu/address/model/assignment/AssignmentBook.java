package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all assignment data.
 * Duplicates are not allowed (by AssignmentId).
 */
public class AssignmentBook {

    private final UniqueAssignmentList assignments = new UniqueAssignmentList();

    public AssignmentBook() {}

    /**
     * Creates an AssignmentBook using the data in {@code toBeCopied}.
     */
    public AssignmentBook(AssignmentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code AssignmentBook} with {@code newData}.
     */
    public void resetData(AssignmentBook newData) {
        requireNonNull(newData);
        setAssignments(newData.getAssignmentList());
    }

    /**
     * Check if an Assignment exists.
     */
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignments.contains(assignment);
    }

    /**
     * Add an assignment to the AssignmentBook
     */
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Remove an assignment from the AssignmentBook
     */
    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    /**
     * Set the Assignment whether it is completed
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        assignments.setAssignment(target, editedAssignment);
    }

    /**
     * Set multiple assignments at once
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setAssignments(assignments);
    }

    /**
     * Returns an unmodifiable view of assignments.
     */
    public ObservableList<Assignment> getAssignmentList() {
        return assignments.asUnmodifiableObservableList();
    }

    public UniqueAssignmentList getUniqueAssignmentList() {
        return assignments;
    }
}
