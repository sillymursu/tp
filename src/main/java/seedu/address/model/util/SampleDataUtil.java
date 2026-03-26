package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new StudentId("S1"),
                 new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    getGroupSet("friends")),

            new Person(new StudentId("S2"),
                 new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    getGroupSet("colleagues")),

            new Person(new StudentId("S3"),
                 new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    getGroupSet("Chinese")),

            new Person(new StudentId("S4"),
                 new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    getGroupSet("Math Remedial")),

            new Person(new StudentId("S5"),
                 new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    getGroupSet("English")),

            new Person(new StudentId("S6"),
                 new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    getGroupSet("GEP"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static Set<Group> getGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }
}
