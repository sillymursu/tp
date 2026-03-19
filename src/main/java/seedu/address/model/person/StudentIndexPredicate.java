package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s matches the {@code Person} given.
 */
public class StudentIndexPredicate implements Predicate<Person> {
    private final Person person;

    public StudentIndexPredicate(Person person) {
        this.person = person;
    }

    @Override
    public boolean test(Person p) {
        return p.equals(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentIndexPredicate)) {
            return false;
        }

        StudentIndexPredicate otherStudentIndexPredicate = (StudentIndexPredicate) other;
        return person.equals(otherStudentIndexPredicate.person);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("person", person).toString();
    }
}
