package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_STUDENT_ID = "S0";
    public static final String DEFAULT_GROUP = "Group";

    private StudentId studentId;
    private Name name;
    private Phone phone;
    private Email email;
    private Set<Group> groups;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        groups = new HashSet<>();
    }

    /**
     * Initializes the {@code PersonBuilder} with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        studentId = personToCopy.getStudentId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        groups = new HashSet<>(personToCopy.getGroups());
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} being built.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} being built.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} being built.
     */
    public PersonBuilder withGroups(String ... groups) {
        this.groups = SampleDataUtil.getGroupSet(groups);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code Person} being built.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} being built.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }


    /**
     * Builds and returns the {@code Person} with the configured fields.
     */
    public Person build() {
        return new Person(studentId, name, phone, email, groups);
    }
}
