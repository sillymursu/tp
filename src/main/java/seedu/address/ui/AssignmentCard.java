package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assignment.Assignment;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class AssignmentCard extends UiPart<Region> {

    private static final String FXML = "AssignmentListCard.fxml";

    public final Assignment assignment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label label;
    @FXML
    private Label group;
    @FXML
    private Label dueDate;
    @FXML
    private Label id;

    /**
     * Creates a {@code AssignmentCard} with the given {@code Assignment} and index to display.
     */
    public AssignmentCard(Assignment assignment, int displayedIndex) {
        super(FXML);
        this.assignment = assignment;
        id.setText(assignment.getAssignmentId().toString() + ". ");
        label.setText(assignment.getLabel().label);
        group.setText("Group: " + assignment.getGroup());
        dueDate.setText("Due by: " + assignment.getDueDate().toString());
    }
}
