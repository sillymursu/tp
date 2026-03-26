package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class GroupNameTest {

    @Test
    public void constructor_toString_success() {
        GroupName groupName = new GroupName("Tutorial-1");
        assertEquals("Tutorial-1", groupName.toString());
    }

    @Test
    public void equals_sameObject_true() {
        GroupName groupName = new GroupName("Tutorial-1");
        assertEquals(groupName, groupName);
    }

    @Test
    public void equals_sameValue_true() {
        GroupName groupName1 = new GroupName("Tutorial-1");
        GroupName groupName2 = new GroupName("Tutorial-1");
        assertEquals(groupName1, groupName2);
    }

    @Test
    public void equals_differentValue_false() {
        GroupName groupName1 = new GroupName("Tutorial-1");
        GroupName groupName2 = new GroupName("Tutorial-2");
        assertNotEquals(groupName1, groupName2);
    }

    @Test
    public void equals_null_false() {
        GroupName groupName = new GroupName("Tutorial-1");
        assertNotEquals(groupName, null);
    }

    @Test
    public void equals_differentType_false() {
        GroupName groupName = new GroupName("Tutorial-1");
        assertNotEquals(groupName, 123);
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        GroupName groupName1 = new GroupName("Tutorial-1");
        GroupName groupName2 = new GroupName("Tutorial-1");
        assertEquals(groupName1.hashCode(), groupName2.hashCode());
    }
}
