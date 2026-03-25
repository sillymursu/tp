package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.person.StudentId;

/**
 * Manages the list of groups in the application.
 */
public class GroupManager {
    private final ArrayList<Group> groups;

    /**
     * Constructs a {@code GroupManager} with the given list of groups.
     *
     * @param groups The initial list of groups to manage.
     */
    public GroupManager(ArrayList<Group> groups) {
        this.groups = groups;
    }

    /**
     * Constructs an empty {@code GroupManager}
     */
    public GroupManager() {
        this.groups = new ArrayList<>();
    }

    /**
     * Initialises the group manager by loading groups from storage,
     * or creating a new set of groups if no storage is found.
     */
    public void initGroupManager() {
        /*  MUST run this on startup!!!
            try to from storage, if no storage found, create groups
            if storage found, load groups
        */
    }

    /**
     * Returns the list of all groups.
     *
     * @return An {@code ArrayList} of {@code Group} objects.
     */
    public ArrayList<Group> getGroups() {
        return this.groups;
    }

    /**
     * Adds a new group with the given name.
     *
     * @param g Group to add.
     */
    public void addGroup(Group g) {
        requireNonNull(g);
        if (validateAddGroup(g)) {
            groups.add(g);
        }
    }

    /**
     * Validates that no group with the given name already exists.
     *
     * @param group The name to check.
     * @return true if no duplicate is found, false if duplicate
     */
    public boolean validateAddGroup(Group group) {
        for (Group g : groups) {
            if (g.getGroupName().equals(group.getGroupName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes the group with the given name, if it exists.
     * This method should be an internal method, not to be called by user,
     * should only be called by other methods.
     * @param group The name of the group to remove.
     */
    public void removeGroup(Group group) {
        for (Group g : groups) {
            if (g.getGroupName().equals(group.getGroupName())) {
                groups.remove(g);
                return;
            }
        }
    }

    /**
     * Adds studentId to group, ignores if student already in group
     * @param g group to add studentId
     * @param id target studentId
     */
    public void addStudentToGroup(Group g, StudentId id) {
        for (Group group : groups) {
            try {
                if (g.getGroupName().equals(group.getGroupName())) {
                    group.addStudent(id);
                }
            } catch (AlreadyInGroupException e) {
                // do nothing
            }
        }
    }

    /**
     * remove studentId to group
     * @param g group to add studentId
     * @param id target studentId
     */
    public void removeStudentFromGroup(Group g, StudentId id) {
        for (Group group : groups) {
            try {
                if (g.getGroupName().equals(group.getGroupName())) {
                    group.removeStudent(id);
                }
            } catch (AlreadyInGroupException e) {
                //do nothing
            }
        }
    }
}
