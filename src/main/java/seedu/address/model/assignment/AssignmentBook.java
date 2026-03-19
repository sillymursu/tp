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

    /**
     * Creates an empty {@code AssignmentBook}.
     */
    public AssignmentBook() {}

    /**
     * Creates an {@code AssignmentBook} using the data in {@code toBeCopied}.
     *
     * @param toBeCopied The assignment book whose data is to be copied.
     */
    public AssignmentBook(AssignmentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code AssignmentBook} with {@code newData}.
     *
     * @param newData The new assignment book data to replace the current data.
     */
    public void resetData(AssignmentBook newData) {
        requireNonNull(newData);
        setAssignments(newData.getAssignmentList());
    }

    /**
     * Returns true if the given assignment exists in this assignment book.
     *
     * @param assignment The assignment to check.
     * @return {@code true} if the assignment exists, {@code false} otherwise.
     */
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignments.contains(assignment);
    }

    /**
     * Adds an assignment to this assignment book.
     *
     * @param assignment The assignment to add.
     */
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Removes an assignment from this assignment book.
     *
     * @param assignment The assignment to remove.
     */
    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    /**
     * Replaces the given target assignment with the edited assignment.
     *
     * @param target The assignment to be replaced.
     * @param editedAssignment The replacement assignment.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        assignments.setAssignment(target, editedAssignment);
    }

    /**
     * Replaces the current list of assignments with the given list.
     *
     * @param assignments The list of assignments to set.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setAssignments(assignments);
    }

    /**
     * Returns an unmodifiable view of the assignments in this assignment book.
     *
     * @return An unmodifiable observable list of assignments.
     */
    public ObservableList<Assignment> getAssignmentList() {
        return assignments.asUnmodifiableObservableList();
    }

    /**
     * Returns the underlying unique assignment list.
     *
     * @return The backing {@code UniqueAssignmentList}.
     */
    public UniqueAssignmentList getUniqueAssignmentList() {
        return assignments;
    }
}
