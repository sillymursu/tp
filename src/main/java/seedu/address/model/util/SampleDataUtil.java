package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.AssignmentList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.StudentList;
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
                    getGroupSet("Math")),

            new Person(new StudentId("S2"),
                 new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    getGroupSet("Math")),

            new Person(new StudentId("S3"),
                 new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    getGroupSet("Math")),

            new Person(new StudentId("S4"),
                 new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    getGroupSet("English")),

            new Person(new StudentId("S5"),
                 new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    getGroupSet("English")),

            new Person(new StudentId("S6"),
                 new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    getGroupSet("Math"))
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group(new GroupName("Math"),
                new StudentList(new ArrayList<>(Arrays.asList(
                    new StudentId("S1"), new StudentId("S2"),
                    new StudentId("S3"), new StudentId("S6")
                ))),
                new AssignmentList(new ArrayList<>(Arrays.asList(
                    new AssignmentId("A1")
                )))),

            new Group(new GroupName("English"),
                new StudentList(new ArrayList<>(Arrays.asList(
                    new StudentId("S4"), new StudentId("S5")
                ))),
                new AssignmentList(new ArrayList<>(Arrays.asList(
                    new AssignmentId("A2")
                )))),
        };
    }

    public static Assignment[] getSampleAssignments() {
        return new Assignment[]{
            new Assignment(new AssignmentId("A1"),
                new Label("Math Homework 1"), getGroupSet("Math"), new DueDate("2026-04-22")),

            new Assignment(new AssignmentId("A2"),
                new Label("English Test"), getGroupSet("English"), new DueDate("2026-04-30"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Assignment sampleAssignment : getSampleAssignments()) {
            sampleAb.addAssignment(sampleAssignment);
        }
        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }
        return sampleAb;
    }

    public static Set<Group> getGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }
}
