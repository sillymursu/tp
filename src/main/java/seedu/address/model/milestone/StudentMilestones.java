package seedu.address.model.milestone;

import seedu.address.model.assignment.AssignmentId;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Stores all milestone records for a single student.
 */
public class StudentMilestones {
    private final Map<AssignmentId, MilestoneRecord> milestonesByAssignment = new HashMap<>();

    /**
     * Constructs an empty {@code StudentMilestones} object.
     */
    public StudentMilestones() {}

    /**
     * Constructs a {@code StudentMilestones} object by copying the contents of another instance.
     *
     * @param toCopy The {@code StudentMilestones} instance whose data should be copied.
     * @throws NullPointerException if {@code toCopy} is null.
     */
    public StudentMilestones(StudentMilestones toCopy) {
        requireNonNull(toCopy);
        milestonesByAssignment.putAll(toCopy.milestonesByAssignment);
    }

    /**
     * Sets or updates the milestone record associated with the specified assignment.
     *
     * @param assignmentId The identifier of the assignment.
     * @param milestoneRecord The milestone record to store.
     * @throws NullPointerException if any argument is null.
     */
    public void set(AssignmentId assignmentId, MilestoneRecord milestoneRecord) {
        requireNonNull(assignmentId);
        requireNonNull(milestoneRecord);
        milestonesByAssignment.put(assignmentId, milestoneRecord);
    }

    /**
     * Returns the milestone record associated with the specified assignment, if it exists.
     *
     * @param assignmentId The identifier of the assignment.
     * @return An {@code Optional} containing the milestone record if present,
     *         otherwise {@code Optional.empty()}.
     * @throws NullPointerException if {@code assignmentId} is null.
     */
    public Optional<MilestoneRecord> get(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        return Optional.ofNullable(milestonesByAssignment.get(assignmentId));
    }

    /**
     * Removes the milestone record associated with the specified assignment.
     *
     * @param assignmentId The identifier of the assignment whose milestone should be removed.
     * @throws NullPointerException if {@code assignmentId} is null.
     */
    public void remove(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        milestonesByAssignment.remove(assignmentId);
    }

    /**
     * Returns true if this student has no milestone records.
     *
     * @return True if no milestones are stored for the student.
     */
    public boolean isEmpty() {
        return milestonesByAssignment.isEmpty();
    }

    /**
     * Returns an unmodifiable view of the assignment-to-milestone mapping.
     *
     * @return An unmodifiable map of {@code AssignmentId} to {@code MilestoneRecord}.
     */
    public Map<AssignmentId, MilestoneRecord> asUnmodifiableMap() {
        return Collections.unmodifiableMap(milestonesByAssignment);
    }

    /**
     * Returns true if the specified object is equal to this {@code StudentMilestones}.
     * Two instances are equal if their milestone mappings are equal.
     *
     * @param other The object to compare with.
     * @return True if both objects represent the same milestone mappings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StudentMilestones)) {
            return false;
        }
        StudentMilestones otherStudentMilestones = (StudentMilestones) other;
        return milestonesByAssignment.equals(otherStudentMilestones.milestonesByAssignment);
    }

    /**
     * Returns the hash code for this {@code StudentMilestones}.
     *
     * @return The hash code computed from the milestone mapping.
     */
    @Override
    public int hashCode() {
        return milestonesByAssignment.hashCode();
    }

    /**
     * Returns the string representation of this student's milestone records.
     *
     * @return A string representation of the assignment-to-milestone mapping.
     */
    @Override
    public String toString() {
        return milestonesByAssignment.toString();
    }
}
