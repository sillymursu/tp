package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedAssignment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";

    private final String assignmentId;
    private final String label;
    private final List<String> groups = new ArrayList<>();
    private final String dueDate;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("assignmentId") String assignmentId,
                             @JsonProperty("label") String label,
                             @JsonProperty("group") List<String> groups,
                             @JsonProperty("dueDate") String dueDate) {
        this.assignmentId = assignmentId;
        this.label = label;
        if (groups != null) {
            this.groups.addAll(groups);
        }
        this.dueDate = dueDate;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        assignmentId = source.getAssignmentId().getValue();
        label = source.getLabel().label;
        groups.addAll(source.getGroups().stream()
                .map(g -> g.getGroupName().toString())
                .collect(Collectors.toList()));
        dueDate = source.getDueDate().toStorageString();
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {

        final AssignmentId modelAssignmentId;
        try {
            modelAssignmentId = (assignmentId == null) ? new AssignmentId("A0") : new AssignmentId(assignmentId);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }

        if (label == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName()));
        }
        if (!Label.isValidLabel(label)) {
            throw new IllegalValueException(Label.MESSAGE_CONSTRAINTS);
        }
        final Label modelLabel = new Label(label);

        if (dueDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DueDate.class.getSimpleName()));
        }
        if (!DueDate.isValidDateFormat(dueDate)) {
            throw new IllegalValueException(DueDate.MESSAGE_CONSTRAINTS);
        }
        final DueDate modelDueDate = new DueDate(dueDate);

        if (groups.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "groups"));
        }

        final Set<Group> modelGroups = new HashSet<>();

        for (String groupName : groups) {
            if (groupName == null || !Group.isValidGroup(groupName)) {
                throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
            }

            modelGroups.add(new Group(groupName));
        }

        return new Assignment(modelAssignmentId, modelLabel, modelGroups, modelDueDate);
    }
}
