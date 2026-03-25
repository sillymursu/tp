package seedu.address.model.group.exceptions;

/**
 * Signals that an operation requires a student to already be in a group, but the student was not found in it.
 */
public class NotInGroupException extends RuntimeException {

    /**
     * Constructs a {@code NotInGroupException} with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public NotInGroupException(String message) {
        super(message);
    }
}
