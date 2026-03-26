package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.StudentId;

public class GroupManagerTest {

    @Test
    public void constructor_startsEmpty() {
        GroupManager manager = new GroupManager();
        assertTrue(manager.getGroups().isEmpty());
    }

    @Test
    public void addGroup_null_throwsNullPointerException() {
        GroupManager manager = new GroupManager();
        assertThrows(NullPointerException.class, () -> manager.addGroup(null));
    }

    @Test
    public void addGroup_newGroup_success() {
        GroupManager manager = new GroupManager();
        Group group = new Group("Tutorial-1");

        manager.addGroup(group);

        assertEquals(1, manager.getGroups().size());
        assertTrue(manager.getGroups().contains(group));
    }

    @Test
    public void addGroup_duplicateName_notAdded() {
        GroupManager manager = new GroupManager();
        Group first = new Group("Tutorial-1");
        Group duplicateName = new Group("Tutorial-1");

        manager.addGroup(first);
        manager.addGroup(duplicateName);

        assertEquals(1, manager.getGroups().size());
        assertTrue(manager.getGroups().contains(first));
    }

    @Test
    public void validateAddGroup_successAndFailure() {
        GroupManager manager = new GroupManager();
        Group existing = new Group("Tutorial-1");
        Group sameName = new Group("Tutorial-1");
        Group differentName = new Group("Tutorial-2");

        assertTrue(manager.validateAddGroup(existing));

        manager.addGroup(existing);

        assertTrue(!manager.validateAddGroup(sameName));
        assertTrue(manager.validateAddGroup(differentName));
    }

    @Test
    public void removeGroup_existingGroup_removed() {
        GroupManager manager = new GroupManager();
        Group group = new Group("Tutorial-1");

        manager.addGroup(group);
        manager.removeGroup(new Group("Tutorial-1"));

        assertTrue(manager.getGroups().isEmpty());
    }

    @Test
    public void removeGroup_missingGroup_noChange() {
        GroupManager manager = new GroupManager();
        Group group = new Group("Tutorial-1");

        manager.addGroup(group);
        manager.removeGroup(new Group("Tutorial-2"));

        assertEquals(1, manager.getGroups().size());
        assertTrue(manager.getGroups().contains(group));
    }

    @Test
    public void addStudentToGroup_matchingGroup_addsStudent() {
        GroupManager manager = new GroupManager();
        Group storedGroup = new Group("Tutorial-1");
        StudentId id1 = StudentIdTestUtil.studentId(1);

        manager.addGroup(storedGroup);
        manager.addStudentToGroup(new Group("Tutorial-1"), id1);

        assertEquals(1, storedGroup.getStudentIds().getStudentList().size());
        assertTrue(storedGroup.getStudentIds().getStudentList().contains(id1));
    }

    @Test
    public void addStudentToGroup_duplicateStudent_ignored() {
        GroupManager manager = new GroupManager();
        Group storedGroup = new Group("Tutorial-1");
        StudentId id1 = StudentIdTestUtil.studentId(1);

        manager.addGroup(storedGroup);
        manager.addStudentToGroup(new Group("Tutorial-1"), id1);
        manager.addStudentToGroup(new Group("Tutorial-1"), id1);

        assertEquals(1, storedGroup.getStudentIds().getStudentList().size());
    }

    @Test
    public void addStudentToGroup_nonMatchingGroup_noChange() {
        GroupManager manager = new GroupManager();
        Group storedGroup = new Group("Tutorial-1");
        StudentId id1 = StudentIdTestUtil.studentId(1);

        manager.addGroup(storedGroup);
        manager.addStudentToGroup(new Group("Tutorial-2"), id1);

        assertTrue(storedGroup.getStudentIds().getStudentList().isEmpty());
    }

    @Test
    public void removeStudentFromGroup_matchingGroup_removesStudent() throws Exception {
        GroupManager manager = new GroupManager();
        Group storedGroup = new Group("Tutorial-1");
        StudentId id1 = StudentIdTestUtil.studentId(1);

        storedGroup.addStudent(id1);
        manager.addGroup(storedGroup);

        assertDoesNotThrow(() -> manager.removeStudentFromGroup(new Group("Tutorial-1"), id1));
        assertTrue(storedGroup.getStudentIds().getStudentList().isEmpty());
    }

    @Test
    public void removeStudentFromGroup_nonMatchingGroup_noChange() throws Exception {
        GroupManager manager = new GroupManager();
        Group storedGroup = new Group("Tutorial-1");
        StudentId id1 = StudentIdTestUtil.studentId(1);

        storedGroup.addStudent(id1);
        manager.addGroup(storedGroup);

        assertDoesNotThrow(() -> manager.removeStudentFromGroup(new Group("Tutorial-2"), id1));
        assertEquals(1, storedGroup.getStudentIds().getStudentList().size());
        assertTrue(storedGroup.getStudentIds().getStudentList().contains(id1));
    }
}
