package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.group.exceptions.NotInGroupException;
import seedu.address.model.person.StudentId;

public class GroupTest {

    @Test
    public void constructor_string_initializesCorrectly() {
        Group group = new Group("Tutorial-1");

        assertEquals(new GroupName("Tutorial-1"), group.getGroupName());
        assertTrue(group.getStudentIds().getStudentList().isEmpty());
    }

    @Test
    public void constructor_groupNameAndStudentList_storesGivenObjects() {
        GroupName groupName = new GroupName("Tutorial-1");
        StudentList studentList = new StudentList();

        Group group = new Group(groupName, studentList);

        assertSame(groupName, group.getGroupName());
        assertSame(studentList, group.getStudentIds());
    }

    @Test
    public void addStudent_null_throwsNullPointerException() {
        Group group = new Group("Tutorial-1");
        assertThrows(NullPointerException.class, () -> group.addStudent(null));
    }

    @Test
    public void addStudent_newStudent_success() {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        Group group = new Group("Tutorial-1");

        assertDoesNotThrow(() -> group.addStudent(id1));
        assertEquals(1, group.getStudentIds().getStudentList().size());
        assertTrue(group.getStudentIds().getStudentList().contains(id1));
    }

    @Test
    public void addStudent_duplicateStudent_throwsAlreadyInGroupException() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        Group group = new Group("Tutorial-1");
        group.addStudent(id1);

        assertThrows(AlreadyInGroupException.class, () -> group.addStudent(id1));
    }

    @Test
    public void removeStudent_null_throwsNullPointerException() {
        Group group = new Group("Tutorial-1");
        assertThrows(NullPointerException.class, () -> group.removeStudent(null));
    }

    @Test
    public void removeStudent_missingStudent_throwsNotInGroupException() {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        Group group = new Group("Tutorial-1");

        assertThrows(NotInGroupException.class, () -> group.removeStudent(id1));
    }

    @Test
    public void removeStudent_existingStudent_success() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        Group group = new Group("Tutorial-1");
        group.addStudent(id1);

        assertDoesNotThrow(() -> group.removeStudent(id1));
        assertTrue(group.getStudentIds().getStudentList().isEmpty());
    }

    @Test
    public void isSameGroup_success() throws Exception {
        Group group = new Group("Tutorial-1");
        Group sameNameDifferentStudents = new Group("Tutorial-1");
        Group differentName = new Group("Tutorial-2");

        StudentId id1 = StudentIdTestUtil.studentId(1);
        sameNameDifferentStudents.addStudent(id1);

        assertTrue(group.isSameGroup(group));
        assertTrue(group.isSameGroup(sameNameDifferentStudents));
        assertTrue(!group.isSameGroup(null));
        assertTrue(!group.isSameGroup(differentName));
    }

    @Test
    public void equals_success() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentId id2 = StudentIdTestUtil.studentId(2);

        Group first = new Group("Tutorial-1");
        Group second = new Group("Tutorial-1");
        Group differentName = new Group("Tutorial-2");

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, "not a group");
        assertEquals(first, second);

        first.addStudent(id1);
        assertNotEquals(first, second);

        second.addStudent(id1);
        assertEquals(first, second);

        second.addStudent(id2);
        assertNotEquals(first, second);

        assertNotEquals(first, differentName);
    }

    @Test
    public void hashCode_sameState_sameHashCode() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);

        Group first = new Group("Tutorial-1");
        Group second = new Group("Tutorial-1");

        first.addStudent(id1);
        second.addStudent(id1);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void toString_containsImportantFields() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        Group group = new Group("Tutorial-1");
        group.addStudent(id1);

        String output = group.toString();

        assertTrue(output.contains("Group Name"));
        assertTrue(output.contains("Tutorial-1"));
        assertTrue(output.contains("Student Ids"));
        assertTrue(output.contains(group.getStudentIds().toString()));
    }
}
