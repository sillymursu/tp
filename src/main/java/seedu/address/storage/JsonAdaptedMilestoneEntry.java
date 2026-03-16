package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.FlatMilestoneEntry;
import seedu.address.model.milestone.MilestoneRecord;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.milestone.StudentId;

/**
 * Jackson-friendly version of a milestone entry.
 */
class JsonAdaptedMilestoneEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Milestone entry's %s field is missing!";
    public static final String INVALID_STATUS_MESSAGE = "Milestone status must be one of: "
            + "NOT_STARTED, IN_PROGRESS, COMPLETED, EXEMPT.";

    private final String studentId;
    private final String assignmentId;
    private final String status;
    private final String completedAt;

    /**
     * Constructs a {@code JsonAdaptedMilestoneEntry} with the given milestone details.
     */
    @JsonCreator
    public JsonAdaptedMilestoneEntry(@JsonProperty("studentId") String studentId,
                                     @JsonProperty("assignmentId") String assignmentId,
                                     @JsonProperty("status") String status,
                                     @JsonProperty("completedAt") String completedAt) {
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.status = status;
        this.completedAt = completedAt;
    }

    /**
     * Converts a given {@code FlatMilestoneEntry} into this class for Jackson use.
     */
    public JsonAdaptedMilestoneEntry(FlatMilestoneEntry source) {
        this.studentId = source.getStudentId().getValue();
        this.assignmentId = source.getAssignmentId().getValue();
        this.status = source.getMilestoneRecord().getStatus().name();
        this.completedAt = source.getMilestoneRecord().getCompletedAt().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted milestone object into the model's {@code FlatMilestoneEntry}.
     */
    public FlatMilestoneEntry toModelType() throws IllegalValueException {
        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "studentId"));
        }
        if (assignmentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "assignmentId"));
        }
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "status"));
        }
        if (completedAt == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "completedAt"));
        }

        final StudentId modelStudentId;
        final AssignmentId modelAssignmentId;
        final MilestoneStatus modelStatus;
        final CompletedAt modelCompletedAt;
        final MilestoneRecord modelMilestoneRecord;

        try {
            modelStudentId = new StudentId(studentId);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }

        try {
            modelAssignmentId = new AssignmentId(assignmentId);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }

        try {
            modelStatus = MilestoneStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(INVALID_STATUS_MESSAGE);
        }

        try {
            modelCompletedAt = new CompletedAt(completedAt);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }

        try {
            modelMilestoneRecord = new MilestoneRecord(modelStatus, modelCompletedAt);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }

        return new FlatMilestoneEntry(modelStudentId, modelAssignmentId, modelMilestoneRecord);
    }
}
