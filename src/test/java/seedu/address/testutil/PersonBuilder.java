package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.milestone.StudentId;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STUDENT_ID = "S0";

    private StudentId studentId;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    public PersonBuilder() {
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    public PersonBuilder(Person personToCopy) {
        studentId = personToCopy.getStudentId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
    }

    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(studentId, name, phone, email, address, tags);
    }
}
