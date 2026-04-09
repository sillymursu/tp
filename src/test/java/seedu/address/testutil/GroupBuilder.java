package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Collections;

import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.group.AssignmentList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.StudentList;
import seedu.address.model.person.StudentId;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_GROUPNAME = "Amy Bee";
    public static final String DEFAULT_STUDENTLIST = "S1";
    public static final String DEFAULT_ASSIGNMENTLIST = "A1";

    private GroupName groupName;
    private StudentList studentList;
    private AssignmentList assignmentList;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        ArrayList<StudentId> studentIds = new ArrayList<StudentId>(
                Collections.singleton(new StudentId(DEFAULT_STUDENTLIST))
        );

        ArrayList<AssignmentId> assignmentIds = new ArrayList<AssignmentId>(
                Collections.singleton(new AssignmentId(DEFAULT_ASSIGNMENTLIST))
        );
        groupName = new GroupName(DEFAULT_GROUPNAME);

        studentList = new StudentList(studentIds);
        assignmentList = new AssignmentList(assignmentIds);
    }

    /**
     * Initializes the {@code GroupBuilder} with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        groupName = groupToCopy.getGroupName();
        studentList = groupToCopy.getStudentIds();
        assignmentList = groupToCopy.getAssignmentIds();
    }

    /**
     * Sets the {@code GroupName} of the {@code Group} that is being built.
     */
    public GroupBuilder withGroupName(String groupName) {
        this.groupName = new GroupName(groupName);
        return this;
    }

    /**
     * Sets the {@code StudentList} of the {@code Group} that is being built.
     */
    public GroupBuilder withStudentList(String ... studentIds) {
        ArrayList<StudentId> studentIdList = new ArrayList<>();
        for (String studentId : studentIds) {
            studentIdList.add(new StudentId(studentId));
        }
        this.studentList = new StudentList(studentIdList);
        return this;
    }

    /**
     * Sets the {@code AssignmentList} of the {@code Group} that is being built.
     */
    public GroupBuilder withAssignmentList(String ... assignmentIds) {
        ArrayList<AssignmentId> assignmentIdList = new ArrayList<>();
        for (String assignmentId : assignmentIds) {
            assignmentIdList.add(new AssignmentId(assignmentId));
        }
        this.assignmentList = new AssignmentList(assignmentIdList);
        return this;
    }

    /**
     * Builds the {@code Group}.
     */
    public Group build() {
        return new Group(groupName, studentList, assignmentList);
    }
}
