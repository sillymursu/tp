package seedu.address.model.group;

import java.util.ArrayList;

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
    public ArrayList<Integer> getStudentIds() {
        return studentIds.getStudentList();
    }
}
