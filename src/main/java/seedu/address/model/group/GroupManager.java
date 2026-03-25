package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.group.exceptions.GroupAlreadyExistsException;

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
     * Adds a new group with the given ID.
     *
     * @param id The ID of the group to add.
     * @throws GroupAlreadyExistsException If a group with the given ID already exists.
     */
    public void addGroup(int id) {
        requireNonNull(id);
        validateAddGroup(id);
        Group g = new Group(id);
        groups.add(g);
    }

    /**
     * Validates that no group with the given ID already exists.
     *
     * @param id The ID to check.
     * @throws GroupAlreadyExistsException If a group with the given ID already exists.
     */
    public void validateAddGroup(int id) throws GroupAlreadyExistsException {
        for (Group g : groups) {
            if (g.getGroupId() == id) {
                throw new GroupAlreadyExistsException(
                        "A group with this ID already exists!");
            }
        }
    }

    /**
     * Removes the group with the given ID, if it exists.
     * This method should be an internal method, not to be called by user,
     * should only be called by other methods.
     * @param id The ID of the group to remove.
     */
    public void removeGroup(int id) {
        for (Group g : groups) {
            if (g.getGroupId() == id) {
                groups.remove(g);
                return;
            }
        }
    }
}
