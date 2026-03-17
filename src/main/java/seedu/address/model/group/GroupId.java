package seedu.address.model.group;

/**
 * Value object representing the groupId of a particular Group.
 */
public final class GroupId {
    private final int id;

    /**
     * Constructs an {@code GroupId} with the given id.
     *
     * @param id The groupId identifier string.
     * @throws NullPointerException if {@code id} is null.
     */
    public GroupId(int id) {
        this.id = id;
    }

    /**
     * Returns the Integer value of this Group identifier.
     *
     * @return The Group identifier as a Integer.
     */
    public int getGroupId() {
        return this.id;
    }
}
