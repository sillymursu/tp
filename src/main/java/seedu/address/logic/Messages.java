package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_INDEX = "Student ID must be in the format S "
            + "followed by 1 to 4 digits (e.g., S1, S301, S9999).";
    public static final String MESSAGE_PERSONS_AND_ASSIGNMENTS_LISTED_OVERVIEW =
                "%1$d persons listed and %2$d assignments listed for Group \"%3$s\"";
    public static final String ADD_MESSAGE_USAGE =
            "add: Adds a student or assignment to LeTutor.\n"
                    + "Use either: \n"
                    + "1. add /students {NAME; PHONE; EMAIL; GROUPS}\n"
                    + "2. add /assignments {LABEL; GROUPS; DUEDATE}\n"
                    + "Examples:\n"
                    + "add /students {John; 9878 0020; johnnyjohn@gmail.com; Sec3A, Sec3B}\n"
                    + "add /assignments {A-JUnit; Sec3A, Sec3B; 2026-02-20}";
    public static final String DELETE_MESSAGE_USAGE =
            "delete: Deletes a student or assignment from LeTutor.\n"
                    + "Use either:\n"
                    + "1. delete /students STUDENT_ID\n"
                    + "2. delete /assignments ASSIGNMENT_ID\n"
                    + "Examples:\n"
                    + "delete /students S1\n"
                    + "delete /assignments A1";
    public static final String EDIT_MESSAGE_USAGE =
            "edit: Edits a student or assignment currently in LeTutor.\n"
                    + "Use either:\n"
                    + "1. edit /students STUDENT_ID {NAME; PHONE; EMAIL; GROUP}\n"
                    + "2. edit /assignments ASSIGNMENT_ID {LABEL; GROUP; DUE_DATE}\n"
                    + "Examples:\n"
                    + "edit /students S2 {John; 98780020; johnnyjohn@gmail.com; S3A}\n"
                    + "edit /assignments A1 {Quiz 2; S3A; 2026-04-01}";
    public static final String FIND_MESSAGE_USAGE =
            "find: Finds students and/or assignments in LeTutor based on keywords or groups.\n"
                    + "Use either:\n"
                    + "1. find /students KEYWORD [MORE_KEYWORDS]...\n"
                    + "2. find /groups GROUPNAME\n"
                    + "Examples:\n"
                    + "find /students alice bob charlie\n"
                    + "find /groups Sec3A";
    public static final String GET_MESSAGE_USAGE =
            "get: Retrieves a student, assignment, or milestone view.\n"
                    + "Use one of:\n"
                    + "1. get /students STUDENT_ID\n"
                    + "2. get /students STUDENT_ID /milestones\n"
                    + "3. get /assignments ASSIGNMENT_ID\n"
                    + "Examples:\n"
                    + "get /students S1\n"
                    + "get /students S1 /milestones\n"
                    + "get /assignments A1";
    public static final String SET_MESSAGE_USAGE =
            "set: Updates one milestone for a student.\n"
                    + "Format:\n"
                    + "set /students STUDENT_ID /milestones ASSIGNMENT_ID STATUS [COMPLETED_AT]\n"
                    + "Allowed statuses: NOT_STARTED, COMPLETED\n"
                    + "If status is NOT_STARTED, do not provide COMPLETED_AT.\n"
                    + "If status is COMPLETED, COMPLETED_AT is required.\n"
                    + "Examples:\n"
                    + "set /students S1 /milestones A1 NOT_STARTED\n"
                    + "set /students S1 /milestones A1 COMPLETED 2026-03-30T1200H";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Group: ")
                .append(person.getGroups().stream()
                        .map(g -> g.getGroupName().name)
                        .collect(Collectors.joining(", ")));
        return builder.toString();
    }

    /**
     * Formats the {@code assignment} for display to the user.
     */
    public static String formatA(Assignment assignment) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Assignment ID: ")
                .append(assignment.getAssignmentId())
                .append("; Label: ")
                .append(assignment.getLabel())
                .append("; Group: ")
                .append(assignment.getGroups().stream()
                        .map(g -> g.getGroupName().name)
                        .collect(Collectors.joining(", ")))
                .append("; Due Date: ")
                .append(assignment.getDueDate());

        return builder.toString();
    }
}
