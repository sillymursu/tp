package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.group.exceptions.NotInGroupException;
import seedu.address.model.person.StudentId;
/**
 * Object representing the group that a student belongs to.
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS = "Group can take any ASCII values, "
            + "and it should not be blank";

    /*
     * The first character of the label must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\x21-\\x7E][\\x20-\\x7E]*$";

    private final GroupName name;
    private final StudentList studentIds;
    private final AssignmentList assignmentIds;

    /**
     * Constructs an {@code Group} with the given value.
     *
     * @param name The assignment identifier string.
     * @throws NullPointerException if {@code name} is null.
     */
    public Group(String name) {
        this.name = new GroupName(name);
        this.studentIds = new StudentList();
        this.assignmentIds = new AssignmentList();
    }

    /**
     * Constructs an {@code Group} with the given value.
     *
     * @param name The assignment identifier string.
     * @throws NullPointerException if {@code name} is null.
     */
    public Group(GroupName name, StudentList students, AssignmentList assignments) {
        this.name = name;
        this.studentIds = students;
        this.assignmentIds = assignments;
    }

    /**
     * Returns true if a given string is a valid group.
     *
     * @param test The string to validate.
     * @return {@code true} if {@code test} is a valid group, {@code false} otherwise.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the GroupName of the group.
     *
     * @return The Group identifier.
     */
    public GroupName getGroupName() {
        return this.name;
    }

    /**
     * Returns the StudentIds that correspond to the Students in the Group.
     *
     * @return The ArrayList of Students as an StudentIds object.
     */
    public StudentList getStudentIds() {
        return this.studentIds;
    }

    /**
     * Checks if the StudentList ArrayList in Group is empty
     *
     * @return True is StudentList is empty and False if otherwise
     */
    public boolean isStudentListEmpty() {
        return this.studentIds.getStudentList().isEmpty();
    }

    /**
     * Adds a student id to this group's list of Students.
     *
     * @param id The student identifier to add.
     * @throws NullPointerException if {@code id} is null.
     * @throws AlreadyInGroupException if {@code id} already exists in this group.
     */
    public void addStudent(StudentId id) throws AlreadyInGroupException {
        requireNonNull(id);
        studentIds.addStudent(id);
    }

    /**
     * Removes a student id from this group's list of Students.
     *
     * @param id The student identifier to remove.
     * @throws NullPointerException if {@code id} is null.
     * @throws NotInGroupException if {@code id} does not exist in this group.
     */
    public void removeStudent(StudentId id) throws NotInGroupException {
        requireNonNull(id);
        studentIds.removeStudent(id);
    }

    /**
     * Returns the AssignmentIds that correspond to the Assignments in the Group.
     *
     * @return The ArrayList of Assignments as an AssignmentIds object.
     */
    public AssignmentList getAssignmentIds() {
        return this.assignmentIds;
    }

    /**
     * Adds an assignment id to this group's list of Students.
     *
     * @param id The assignment identifier to add.
     * @throws NullPointerException if {@code id} is null.
     * @throws AlreadyInGroupException if {@code id} already exists in this group.
     */
    public void addAssignment(AssignmentId id) throws AlreadyInGroupException {
        requireNonNull(id);
        assignmentIds.addAssignment(id);
    }

    /**
     * Removes an assignment id from this group's list of Assignments.
     *
     * @param id The assignment identifier to remove.
     * @throws NullPointerException if {@code id} is null.
     * @throws NotInGroupException if {@code id} does not exist in this group.
     */
    public void removeAssignment(AssignmentId id) throws NotInGroupException {
        requireNonNull(id);
        assignmentIds.removeAssignment(id);
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.name.equals(name);
    }

    /**
     * Checks if the AssignmentList ArrayList in Group is empty
     *
     * @return True if AssignmentList is empty and False if otherwise
     */
    public boolean isAssignmentListEmpty() {
        return assignmentIds.getAssignmentList().isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return name.equals(otherGroup.name);

    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Group Name", name)
                .add("Student Ids", studentIds)
                .add("Assignment Ids", assignmentIds)
                .toString();
    }
}
