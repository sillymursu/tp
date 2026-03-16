package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignment.Assignment;

/**
 * Panel containing the list of assignments.
 */
public class AssignmentListPanel extends UiPart<Region> {
    private static final String FXML = "AssignmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AssignmentListPanel.class);

    @FXML
    private ListView<Assignment> assignmentListView;

    /**
     * Creates a {@code AssignmentListPanel} with the given {@code ObservableList}.
     */
    public AssignmentListPanel(ObservableList<Assignment> assignmentList) {
        super(FXML);
        assignmentListView.setItems(assignmentList);
        assignmentListView.setCellFactory(listView -> new AssignmentListPanel.AssignmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Assignment} using a {@code AssignmentCard}.
     */
    class AssignmentListViewCell extends ListCell<Assignment> {
        @Override
        protected void updateItem(Assignment assg, boolean empty) {
            super.updateItem(assg, empty);

            if (empty || assg == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssignmentCard(assg, getIndex() + 1).getRoot());
            }
        }
    }

}
