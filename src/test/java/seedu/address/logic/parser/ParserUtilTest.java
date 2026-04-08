package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;

public class ParserUtilTest {

    public static final String VALID_GROUP = "Hello";

    private static final String VALID_STUDENT_ID = "S1";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "98123456";
    private static final String VALID_EMAIL = "rachel@example.com";

    private static final String INVALID_STUDENT_ID = "1";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_GROUP = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_ASSIGNMENT_ID = "A1";
    private static final String VALID_LABEL = "A-TEST";
    private static final String VALID_DUEDATE = "2026-04-20";

    private static final String INVALID_ASSIGNMENT_ID = "1";
    private static final String INVALID_LABEL = " ";
    private static final String INVALID_DUEDATE = "20-04-2026";

    private static final String WHITESPACE = " \t\r\n";
    private static final String TUPLE3_ERROR_MESSAGE = "dummy usage";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseGroup_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroup((String) null));
    }

    @Test
    public void parseGroup_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroup(INVALID_GROUP));
    }

    @Test
    public void parseGroup_validValueWithoutWhitespace_returnsGroup() throws Exception {
        Group expectedGroup = new Group(VALID_GROUP);
        assertEquals(expectedGroup, ParserUtil.parseGroup(VALID_GROUP));
    }

    @Test
    public void parseGroup_validValueWithWhitespace_returnsTrimmedGroup() throws Exception {
        String groupWithWhitespace = WHITESPACE + VALID_GROUP + WHITESPACE;
        Group expectedGroup = new Group(VALID_GROUP);
        assertEquals(expectedGroup, ParserUtil.parseGroup(groupWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseStudentId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId((String) null));
    }

    @Test
    public void parseStudentId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(INVALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithoutWhitespace_returnsStudentId() throws Exception {
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(VALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithWhitespace_returnsStudentId() throws Exception {
        String studentIdWithWhitespace = WHITESPACE + VALID_STUDENT_ID + WHITESPACE;
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(studentIdWithWhitespace));
    }

    @Test
    public void parseAssignmentId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAssignmentId((String) null));
    }

    @Test
    public void parseAssignmentId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAssignmentId(INVALID_ASSIGNMENT_ID));
    }

    @Test
    public void parseAssignmentId_validValueWithoutWhitespace_returnsAssignmentId() throws Exception {
        AssignmentId expectedAssignmentId = new AssignmentId(VALID_ASSIGNMENT_ID);
        assertEquals(expectedAssignmentId, ParserUtil.parseAssignmentId(VALID_ASSIGNMENT_ID));
    }

    @Test
    public void parseAssignmentId_validValueWithWhitespace_returnsAssignmentId() throws Exception {
        String assignmentIdWithWhitespace = WHITESPACE + VALID_ASSIGNMENT_ID + WHITESPACE;
        AssignmentId expectedAssignmentId = new AssignmentId(VALID_ASSIGNMENT_ID);
        assertEquals(expectedAssignmentId, ParserUtil.parseAssignmentId(assignmentIdWithWhitespace));
    }

    @Test
    public void parseLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLabel((String) null));
    }

    @Test
    public void parseLabel_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLabel(INVALID_LABEL));
    }

    @Test
    public void parseLabel_validValueWithoutWhitespace_returnsLabel() throws Exception {
        Label expectedLabel = new Label(VALID_LABEL);
        assertEquals(expectedLabel, ParserUtil.parseLabel(VALID_LABEL));
    }

    @Test
    public void parseLabel_validValueWithWhitespace_returnsLabel() throws Exception {
        String labelWithWhitespace = WHITESPACE + VALID_LABEL + WHITESPACE;
        Label expectedLabel = new Label(VALID_LABEL);
        assertEquals(expectedLabel, ParserUtil.parseLabel(labelWithWhitespace));
    }

    @Test
    public void parseDueDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDueDate((String) null));
    }

    @Test
    public void parseDueDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDueDate(INVALID_DUEDATE));
    }

    @Test
    public void parseDueDate_validValueWithoutWhitespace_returnsDueDate() throws Exception {
        DueDate expectedDueDate = new DueDate(VALID_DUEDATE);
        assertEquals(expectedDueDate, ParserUtil.parseDueDate(VALID_DUEDATE));
    }

    @Test
    public void parseDueDate_validValueWithWhitespace_returnsDueDate() throws Exception {
        String dueDateWithWhitespace = WHITESPACE + VALID_DUEDATE + WHITESPACE;
        DueDate expectedDueDate = new DueDate(VALID_DUEDATE);
        assertEquals(expectedDueDate, ParserUtil.parseDueDate(dueDateWithWhitespace));
    }

    @Test
    public void parseGroups_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroups(null));
    }

    @Test
    public void parseGroups_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroups(new String[]{VALID_GROUP, INVALID_GROUP}));
    }

    @Test
    public void parseTuple3AllowEmpty_null_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TUPLE3_ERROR_MESSAGE);
        assertThrows(ParseException.class, expectedMessage, () ->
                ParserUtil.parseTuple3AllowEmpty(null, TUPLE3_ERROR_MESSAGE));
    }

    @Test
    public void parseTuple3AllowEmpty_validTuple_success() throws Exception {
        assertEquals(java.util.Arrays.asList("A-TEST", "G1", "2026-04-20"),
                ParserUtil.parseTuple3AllowEmpty("{A-TEST; G1; 2026-04-20}", TUPLE3_ERROR_MESSAGE));
    }

    @Test
    public void parseTuple3AllowEmpty_emptyValue_success() throws Exception {
        assertEquals(java.util.Arrays.asList("A-TEST", "", "2026-04-20"),
                ParserUtil.parseTuple3AllowEmpty("{A-TEST; ; 2026-04-20}", TUPLE3_ERROR_MESSAGE));


        assertEquals(java.util.Arrays.asList("", "", "2026-04-20"),
                ParserUtil.parseTuple3AllowEmpty("{ ; ; 2026-04-20}", TUPLE3_ERROR_MESSAGE));

        assertEquals(java.util.Arrays.asList("", "", ""),
                ParserUtil.parseTuple3AllowEmpty("{ ; ; }", TUPLE3_ERROR_MESSAGE));
    }

    @Test
    public void parseTuple3AllowEmpty_invalidShape_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TUPLE3_ERROR_MESSAGE);

        assertThrows(ParseException.class, expectedMessage, () ->
                ParserUtil.parseTuple3AllowEmpty("A-TEST; G1; 2026-04-20", TUPLE3_ERROR_MESSAGE));

        assertThrows(ParseException.class, expectedMessage, () ->
                ParserUtil.parseTuple3AllowEmpty("{A-TEST; G1}", TUPLE3_ERROR_MESSAGE));

        assertThrows(ParseException.class, expectedMessage, () ->
                ParserUtil.parseTuple3AllowEmpty("{A-TEST; G1; 2026-04-20; extra}", TUPLE3_ERROR_MESSAGE));
    }

    @Test
    public void parseTuple3_validTuple_success() throws Exception {
        assertEquals(java.util.Arrays.asList("A-TEST", "G1", "2026-04-20"),
                ParserUtil.parseTuple3("{ A-TEST ; G1 ; 2026-04-20 }", TUPLE3_ERROR_MESSAGE));
    }

    @Test
    public void parseTuple3_emptyRequiredValues_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TUPLE3_ERROR_MESSAGE);

        assertThrows(ParseException.class, expectedMessage, () ->
                ParserUtil.parseTuple3("{ ; G1; 2026-04-20}", TUPLE3_ERROR_MESSAGE));

        assertThrows(ParseException.class, expectedMessage, () ->
                ParserUtil.parseTuple3("{A-TEST; ; 2026-04-20}", TUPLE3_ERROR_MESSAGE));

        assertThrows(ParseException.class, expectedMessage, () ->
                ParserUtil.parseTuple3("{A-TEST; G1; }", TUPLE3_ERROR_MESSAGE));
    }
}
