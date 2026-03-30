package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents one stored milestone record for a student-assignment pair.
 */
public class MilestoneRecord {

    private final MilestoneStatus status;
    private final CompletedAt completedAt;

    /**
     * Constructs a {@code MilestoneRecord}.
     *
     * @param status The stored milestone status.
     * @param completedAt The completion timestamp. Must be empty unless status is COMPLETED.
     */
    public MilestoneRecord(MilestoneStatus status, CompletedAt completedAt) {
        requireNonNull(status);
        requireNonNull(completedAt);

        if (status == MilestoneStatus.NOT_STARTED && !completedAt.getValue().isBlank()) {
            throw new IllegalArgumentException(
                    "completedAt must be empty when milestone status is NOT_STARTED");
        }

        this.status = status;
        this.completedAt = completedAt;
    }

    public MilestoneStatus getStatus() {
        return status;
    }

    public CompletedAt getCompletedAt() {
        return completedAt;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MilestoneRecord)) {
            return false;
        }
        MilestoneRecord otherMilestoneRecord = (MilestoneRecord) other;
        return status.equals(otherMilestoneRecord.status)
                && completedAt.equals(otherMilestoneRecord.completedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, completedAt);
    }

    @Override
    public String toString() {
        return "MilestoneRecord{"
                + "status=" + status
                + ", completedAt=" + completedAt
                + "}";
    }
}
