package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.milestone.StudentId;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    ReadOnlyUserPrefs getUserPrefs();

    GuiSettings getGuiSettings();

    void setGuiSettings(GuiSettings guiSettings);

    Path getAddressBookFilePath();

    void setAddressBookFilePath(Path addressBookFilePath);

    void setAddressBook(ReadOnlyAddressBook addressBook);

    ReadOnlyAddressBook getAddressBook();

    boolean hasPerson(Person person);

    void deletePerson(Person target);

    void addPerson(Person person);

    void setPerson(Person target, Person editedPerson);

    ObservableList<Person> getFilteredPersonList();

    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Generates the next available StudentId in the form S1, S2, S3... based on existing persons.
     */
    StudentId getNextStudentId();
}