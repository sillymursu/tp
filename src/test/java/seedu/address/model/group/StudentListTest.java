package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.group.exceptions.NotInGroupException;
import seedu.address.model.person.StudentId;

public class StudentListTest {

    @Test
    public void constructor_noArgs_startsEmpty() {
        StudentList studentList = new StudentList();
        assertTrue(studentList.getStudentList().isEmpty());
    }

    @Test
    public void constructor_withArrayList_copiesContents() {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentId id2 = StudentIdTestUtil.studentId(2);

        ArrayList<StudentId> source = new ArrayList<>();
        source.add(id1);

        StudentList studentList = new StudentList(source);
        source.add(id2);

        assertEquals(List.of(id1), studentList.getStudentList());
    }

    @Test
    public void getStudentList_returnsUnderlyingContents() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);

        StudentList studentList = new StudentList();
        studentList.addStudent(id1);

        assertEquals(List.of(id1), studentList.getStudentList());
    }

    @Test
    public void addStudent_null_throwsNullPointerException() {
        StudentList studentList = new StudentList();
        assertThrows(NullPointerException.class, () -> studentList.addStudent(null));
    }

    @Test
    public void addStudent_newStudent_success() {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentList studentList = new StudentList();

        assertDoesNotThrow(() -> studentList.addStudent(id1));
        assertEquals(List.of(id1), studentList.getStudentList());
    }

    @Test
    public void addStudent_duplicateStudent_throwsAlreadyInGroupException() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentList studentList = new StudentList();
        studentList.addStudent(id1);

        assertThrows(AlreadyInGroupException.class, () -> studentList.addStudent(id1));
    }

    @Test
    public void removeStudent_null_throwsNullPointerException() {
        StudentList studentList = new StudentList();
        assertThrows(NullPointerException.class, () -> studentList.removeStudent(null));
    }

    @Test
    public void removeStudent_missingStudent_throwsNotInGroupException() {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentList studentList = new StudentList();

        assertThrows(NotInGroupException.class, () -> studentList.removeStudent(id1));
    }

    @Test
    public void removeStudent_existingStudent_success() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentList studentList = new StudentList();
        studentList.addStudent(id1);

        assertDoesNotThrow(() -> studentList.removeStudent(id1));
        assertTrue(studentList.getStudentList().isEmpty());
    }

    @Test
    public void toString_reflectsContents() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentList studentList = new StudentList();
        studentList.addStudent(id1);

        assertEquals(studentList.getStudentList().toString(), studentList.toString());
    }

    @Test
    public void equals_success() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);
        StudentId id2 = StudentIdTestUtil.studentId(2);

        StudentList first = new StudentList();
        StudentList second = new StudentList();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, "not a student list");

        assertEquals(first, second);

        first.addStudent(id1);
        assertNotEquals(first, second);

        second.addStudent(id1);
        assertEquals(first, second);

        second.addStudent(id2);
        assertNotEquals(first, second);
    }

    @Test
    public void hashCode_sameContents_sameHashCode() throws Exception {
        StudentId id1 = StudentIdTestUtil.studentId(1);

        StudentList first = new StudentList();
        StudentList second = new StudentList();

        first.addStudent(id1);
        second.addStudent(id1);

        assertEquals(first.hashCode(), second.hashCode());
    }
}
