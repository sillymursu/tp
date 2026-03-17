package seedu.address.model.group;

import java.util.ArrayList;

/**
 * Value object representing the list of Students in a particular Group.
 * Stores a list of StudentIds that correspond to the Students in a particular Group.
 */
public class StudentList {
    private final ArrayList<Integer> list;

    /**
     * Constructs an {@code StudentList}.
     */
    public StudentList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the ArrayList of Students that correspond to the Students in the Group.
     * @return The ArrayList of Students as an ArrayList.
     */
    public ArrayList<Integer> getStudentList() {
        return this.list;
    }

    public void addStudent() {

    }
}
