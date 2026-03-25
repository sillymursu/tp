package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.group.exceptions.NotInGroupException;
import seedu.address.model.person.StudentId;

/**
 * Object representing the group that a student belongs to.
 */
public class Group {
    private final GroupId groupId;
    private final StudentList studentIds;

    /**
     * Constructs an {@code Group} with the given value.
     *
     * @param id The assignment identifier string.
     * @throws NullPointerException if {@code id} is null.
     */
    public Group(int id) {
        this.groupId = new GroupId(id);
        this.studentIds = new StudentList();
    }

    /**
     * Returns the Integer value of this Group identifier.
     *
     * @return The Group identifier as a Integer.
     */
    public int getGroupId() {
        return this.groupId.getGroupId();
    }

    /**
     * Returns the ArrayList of Students that correspond to the Students in the Group.
     *
     * @return The ArrayList of Students as an ArrayList.
     */
    public ArrayList<StudentId> getStudentIds() {
        return studentIds.getStudentList();
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
}
