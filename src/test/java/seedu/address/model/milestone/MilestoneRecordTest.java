package seedu.address.model.milestone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MilestoneRecordTest {

    @Test
    public void constructor_notStartedWithNonEmptyCompletedAt_throwsIllegalArgumentException() {
        CompletedAt completedAt = new CompletedAt("2026-03-24T1200H");

        assertThrows(IllegalArgumentException.class, () ->
                new MilestoneRecord(MilestoneStatus.NOT_STARTED, completedAt));
    }

    @Test
    public void constructor_completedWithNonEmptyCompletedAt_success() {
        CompletedAt completedAt = new CompletedAt("2026-03-24T1200H");
        MilestoneRecord milestoneRecord = new MilestoneRecord(MilestoneStatus.COMPLETED, completedAt);

        assertEquals(MilestoneStatus.COMPLETED, milestoneRecord.getStatus());
        assertEquals(completedAt, milestoneRecord.getCompletedAt());
    }

    @Test
    public void constructor_notStartedWithEmptyCompletedAt_success() {
        CompletedAt completedAt = new CompletedAt("");
        MilestoneRecord milestoneRecord = new MilestoneRecord(MilestoneStatus.NOT_STARTED, completedAt);

        assertEquals(MilestoneStatus.NOT_STARTED, milestoneRecord.getStatus());
        assertEquals(completedAt, milestoneRecord.getCompletedAt());
    }

    @Test
    public void equals() {
        MilestoneRecord firstRecord = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-24T1200H"));

        MilestoneRecord sameRecord = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-24T1200H"));

        MilestoneRecord differentRecord = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));

        assertEquals(firstRecord, firstRecord);
        assertEquals(firstRecord, sameRecord);

        assertNotEquals(firstRecord, null);
        assertNotEquals(firstRecord, "not a MilestoneRecord");
        assertNotEquals(firstRecord, differentRecord);
    }
}