package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final StudentId studentId;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Set<Group> groups;

    /**
     * Every field must be present and not null.
     */
    public Person(StudentId studentId, Name name, Phone phone, Email email, Set<Group> groups) {
        requireAllNonNull(studentId, name, phone, email, groups);
        this.studentId = studentId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.groups = new HashSet<>(groups);
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    /**
     * Getter that find the {@Code GroupName} of all Groups
     * @return the GroupName of all the groups
     */
    private Set<GroupName> getGroupNames() {
        return groups.stream()
                .map(Group::getGroupName)
                .collect(Collectors.toSet());
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }
    /**
     * Returns true if both persons have the same phone.
     */
    public boolean hasSamePhone(Person otherPerson) {
        return otherPerson != null
                && otherPerson.getPhone().equals(getPhone());
    }
    /**
     * Returns true if both persons have the same email.
     */
    public boolean hasSameEmail(Person otherPerson) {
        return otherPerson != null
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person otherPerson = (Person) other;
        Set<GroupName> thisNames = getGroupNames();
        Set<GroupName> otherNames = otherPerson.getGroupNames();
        return studentId.equals(otherPerson.studentId)
                && name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && thisNames.equals(otherNames);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentId, name, phone, email, groups);
    }

    @Override
    public String toString() {
        // StudentId is backend-only; keep it out of user-facing strings.
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("group", groups)
                .toString();
    }
}
