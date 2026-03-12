package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Top-level in-memory milestone store.
 *
 * Structure:
 * studentId -> (assignmentId -> milestoneRecord)
 */
public class MilestoneStore {
    private final Map<StudentId, StudentMilestones> studentMilestones = new HashMap<>();

    public MilestoneStore() {}

    /**
     * Constructs a {@code MilestoneStore} by copying the contents of another store.
     *
     * @param toCopy The milestone store whose data should be copied.
     * @throws NullPointerException if {@code toCopy} is null.
     */
    public MilestoneStore(MilestoneStore toCopy) {
        requireNonNull(toCopy);
        setMilestones(toCopy);
    }

    /**
     * Replaces the contents of this milestone store with the data from the given store.
     *
     * @param replacement The milestone store whose data will replace the current data.
     * @throws NullPointerException if {@code replacement} is null.
     */
    public void setMilestones(MilestoneStore replacement) {
        requireNonNull(replacement);
        studentMilestones.clear();
        for (Map.Entry<StudentId, StudentMilestones> entry : replacement.studentMilestones.entrySet()) {
            studentMilestones.put(entry.getKey(), new StudentMilestones(entry.getValue()));
        }
    }

    /**
     * Sets or updates the milestone record for the specified student and assignment.
     * If the student does not already have milestone records, a new entry will be created.
     *
     * @param studentId The identifier of the student.
     * @param assignmentId The identifier of the assignment.
     * @param milestoneRecord The milestone record to associate with the student-assignment pair.
     * @throws NullPointerException if any argument is null.
     */
    public void setMilestone(StudentId studentId, AssignmentId assignmentId, MilestoneRecord milestoneRecord) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);
        requireNonNull(milestoneRecord);

        studentMilestones
                .computeIfAbsent(studentId, unused -> new StudentMilestones())
                .set(assignmentId, milestoneRecord);
    }

    /**
     * Returns the milestone record for the specified student and assignment, if it exists.
     *
     * @param studentId The identifier of the student.
     * @param assignmentId The identifier of the assignment.
     * @return An {@code Optional} containing the milestone record if present,
     *         otherwise {@code Optional.empty()}.
     * @throws NullPointerException if any argument is null.
     */
    public Optional<MilestoneRecord> getMilestone(StudentId studentId, AssignmentId assignmentId) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);

        if (!studentMilestones.containsKey(studentId)) {
            return Optional.empty();
        }

        return studentMilestones.get(studentId).get(assignmentId);
    }

    /**
     * Returns a copy of the milestone records associated with the given student.
     * If the student has no milestones recorded, an empty {@code StudentMilestones}
     * object is returned.
     *
     * @param studentId The identifier of the student.
     * @return A copy of the student's milestone records.
     * @throws NullPointerException if {@code studentId} is null.
     */
    public StudentMilestones getStudentMilestones(StudentId studentId) {
        requireNonNull(studentId);
        StudentMilestones milestones = studentMilestones.get(studentId);
        return milestones == null ? new StudentMilestones() : new StudentMilestones(milestones);
    }

    /**
     * Removes all milestone records associated with the specified student.
     *
     * @param studentId The identifier of the student whose milestones should be removed.
     * @throws NullPointerException if {@code studentId} is null.
     */
    public void removeAllForStudent(StudentId studentId) {
        requireNonNull(studentId);
        studentMilestones.remove(studentId);
    }

    /**
     * Removes all milestone records associated with the specified assignment.
     * Any student entries that no longer contain milestones after the removal
     * will also be removed from the store.
     *
     * @param assignmentId The identifier of the assignment whose milestones should be removed.
     * @throws NullPointerException if {@code assignmentId} is null.
     */
    public void removeAllForAssignment(AssignmentId assignmentId) {
        requireNonNull(assignmentId);

        List<StudentId> emptyStudents = new ArrayList<>();
        for (Map.Entry<StudentId, StudentMilestones> entry : studentMilestones.entrySet()) {
            entry.getValue().remove(assignmentId);
            if (entry.getValue().isEmpty()) {
                emptyStudents.add(entry.getKey());
            }
        }

        for (StudentId studentId : emptyStudents) {
            studentMilestones.remove(studentId);
        }
    }

    /**
     * Returns true if this milestone store contains no milestone records.
     *
     * @return True if the store is empty.
     */
    public boolean isEmpty() {
        return studentMilestones.isEmpty();
    }

    /**
     * Converts the nested milestone structure into a flat list representation.
     * Each entry represents one student-assignment milestone record.
     *
     * @return A list of {@code FlatMilestoneEntry} objects representing all stored milestones.
     */
    public List<FlatMilestoneEntry> toFlatList() {
        List<FlatMilestoneEntry> flatEntries = new ArrayList<>();
        for (Map.Entry<StudentId, StudentMilestones> studentEntry : studentMilestones.entrySet()) {
            StudentId studentId = studentEntry.getKey();
            for (Map.Entry<AssignmentId, MilestoneRecord> assignmentEntry
                    : studentEntry.getValue().asUnmodifiableMap().entrySet()) {
                flatEntries.add(new FlatMilestoneEntry(
                        studentId,
                        assignmentEntry.getKey(),
                        assignmentEntry.getValue()));
            }
        }
        return flatEntries;
    }

    /**
     * Returns true if the specified object is equal to this milestone store.
     * Two stores are considered equal if their internal milestone mappings are equal.
     *
     * @param other The object to compare with.
     * @return True if the objects represent the same milestone store.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MilestoneStore)) {
            return false;
        }
        MilestoneStore otherStore = (MilestoneStore) other;
        return studentMilestones.equals(otherStore.studentMilestones);
    }

    /**
     * Returns the hash code for this milestone store.
     *
     * @return The hash code based on the internal milestone mapping.
     */
    @Override
    public int hashCode() {
        return studentMilestones.hashCode();
    }

    /**
     * Returns the string representation of this milestone store.
     *
     * @return A string representation of the internal milestone mapping.
     */
    @Override
    public String toString() {
        return studentMilestones.toString();
    }
}
