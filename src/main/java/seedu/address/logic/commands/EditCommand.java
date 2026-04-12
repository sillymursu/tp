package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " /students: Edits the details of a student\n"
            + "Format: edit /students STUDENT_ID {NAME; PHONE; EMAIL; GROUP}\n"
            + "Example: edit /students S2 {John; 98780020; johnnyjohn@gmail.com; S3A}";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_DUPLICATE_PHONE = "Another person with the same phone number"
            + " already exists in the address book";
    public static final String MESSAGE_DUPLICATE_EMAIL = "Another person with the same email"
            + " already exists in the address book";

    private final StudentId studentId;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param studentId of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(StudentId studentId, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(studentId);
        requireNonNull(editPersonDescriptor);

        this.studentId = studentId;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToEdit = null;
        for (Person person : lastShownList) {
            if (person.getStudentId().equals(studentId)) {
                personToEdit = person;
                break;
            }
        }

        if (personToEdit == null) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        if (hasDuplicatePhone(model, personToEdit, editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PHONE);
        }
        if (hasDuplicateEmail(model, personToEdit, editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMAIL);
        }

        model.setPerson(personToEdit, editedPerson);
        for (Group g : personToEdit.getGroups()) {
            model.removeStudentFromGroup(g, personToEdit.getStudentId());
        }
        for (Group g : editedPerson.getGroups()) {
            model.addGroup(g);
            model.addStudentToGroup(g, editedPerson.getStudentId());
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Returns true if another person (excluding the one being edited) already has the edited phone.
     */
    private boolean hasDuplicatePhone(Model model, Person personToEdit, Person editedPerson) {
        return model.getAddressBook().getPersonList().stream()
                .anyMatch(person -> !person.getStudentId().equals(personToEdit.getStudentId())
                        && person.hasSamePhone(editedPerson));
    }

    /**
     * Returns true if another person (excluding the one being edited) already has the edited email.
     */
    private boolean hasDuplicateEmail(Model model, Person personToEdit, Person editedPerson) {
        return model.getAddressBook().getPersonList().stream()
                .anyMatch(person -> !person.getStudentId().equals(personToEdit.getStudentId())
                        && person.hasSameEmail(editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Set<Group> updatedGroups = editPersonDescriptor.getGroups().orElse(personToEdit.getGroups());

        return new Person(
                personToEdit.getStudentId(),
                updatedName,
                updatedPhone,
                updatedEmail,
                updatedGroups
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return studentId.equals(otherEditCommand.studentId)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Group> groups;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setGroups(toCopy.groups);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, groups);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name).filter(n -> !n.fullName.isEmpty());
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone).filter(p -> !p.value.isEmpty());
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email).filter(e -> !e.value.isEmpty());
        }

        public void setGroups(Set<Group> groups) {
            this.groups = (groups != null) ? new HashSet<>(groups) : null;
        }

        public Optional<Set<Group>> getGroups() {
            return (groups != null) ? Optional.of(Collections.unmodifiableSet(groups))
                                                        : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(groups, otherEditPersonDescriptor.groups);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("group", groups)
                    .toString();
        }
    }
}
