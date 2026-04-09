package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group CS = new GroupBuilder()
            .withGroupName("CS")
            .withStudentList("S1", "S2", "S3", "S4", "S5")
            .withAssignmentList("A6")
            .build();

    public static final Group SEC3A = new GroupBuilder()
            .withGroupName("Sec3A")
            .withStudentList("S6")
            .withAssignmentList("A1", "A2")
            .build();

    public static final Group SEC3B = new GroupBuilder()
            .withGroupName("Sec3B")
            .withStudentList("S7")
            .withAssignmentList("A3", "A4")
            .build();

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(CS, SEC3A, SEC3B));
    }

    public static AddressBook getTypicalGroupBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        return ab;
    }
}
