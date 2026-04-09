package seedu.address.model.group;

/**
 * Value object representing the groupId of a particular Group.
 */
public final class GroupName {
    public final String name;

    /**
     * Constructs an {@code GroupName} with the given id.
     *
     * @param name The group name.
     * @throws NullPointerException if {@code name} is null.
     */
    public GroupName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupName)) {
            return false;
        }

        GroupName otherGroup = (GroupName) other;
        return name.equals(otherGroup.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
