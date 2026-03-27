package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usages: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson(new PersonBuilder().build()).build();}<br>
 *     {@code AddressBook ab = new AddressBookBuilder().withAssignment(TypicalAssignments.A_JUNIT).build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    /**
     * Creates a builder backed by a new {@code addressBook}.
     */
    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    /**
     * Creates a builder backed by the given {@code addressBook}.
     */
    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Assignment} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withAssignment(Assignment assignment) {
        addressBook.addAssignment(assignment);
        return this;
    }

    /**
     * Returns the constructed {@code AddressBook}.
     */
    public AddressBook build() {
        return addressBook;
    }
}
