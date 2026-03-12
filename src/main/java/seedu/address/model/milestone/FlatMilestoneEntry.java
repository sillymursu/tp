package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Flat representation of one milestone entry for storage and reconstruction.
 */
public final class FlatMilestoneEntry {
    private final StudentId studentId;
    private final AssignmentId assignmentId;
    private final MilestoneRecord milestoneRecord;

    /**
     * Constructs a {@code FlatMilestoneEntry} with the specified identifiers and milestone record.
     *
     * @param studentId The identifier of the student associated with this milestone entry.
     * @param assignmentId The identifier of the assignment associated with this milestone entry.
     * @param milestoneRecord The milestone record containing the status and completion data.
     * @throws NullPointerException if any argument is null.
     */
    public FlatMilestoneEntry(StudentId studentId, AssignmentId assignmentId, MilestoneRecord milestoneRecord) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);
        requireNonNull(milestoneRecord);

        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.milestoneRecord = milestoneRecord;
    }

    /**
     * Returns the student identifier associated with this milestone entry.
     *
     * @return The {@code StudentId} of the student.
     */
    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Returns the assignment identifier associated with this milestone entry.
     *
     * @return The {@code AssignmentId} of the assignment.
     */
    public AssignmentId getAssignmentId() {
        return assignmentId;
    }

    /**
     * Returns the milestone record stored in this entry.
     *
     * @return The {@code MilestoneRecord} containing milestone status information.
     */
    public MilestoneRecord getMilestoneRecord() {
        return milestoneRecord;
    }

    /**
     * Returns true if the specified object is equal to this milestone entry.
     * Two {@code FlatMilestoneEntry} objects are considered equal if their
     * student identifiers, assignment identifiers, and milestone records are equal.
     *
     * @param other The object to compare with.
     * @return True if the objects represent the same milestone entry.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FlatMilestoneEntry)) {
            return false;
        }
        FlatMilestoneEntry otherEntry = (FlatMilestoneEntry) other;
        return studentId.equals(otherEntry.studentId)
                && assignmentId.equals(otherEntry.assignmentId)
                && milestoneRecord.equals(otherEntry.milestoneRecord);
    }

    /**
     * Returns the hash code for this milestone entry.
     * The hash code is computed based on the student identifier,
     * assignment identifier, and milestone record.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + assignmentId.hashCode();
        result = 31 * result + milestoneRecord.hashCode();
        return result;
    }

    /**
     * Returns the string representation of this milestone entry.
     *
     * @return A string containing the student id, assignment id,
     *     and milestone record information.
     */
    @Override
    public String toString() {
        return "FlatMilestoneEntry{studentId=" + studentId
                + ", assignmentId=" + assignmentId
                + ", milestoneRecord=" + milestoneRecord + "}";
    }
}
