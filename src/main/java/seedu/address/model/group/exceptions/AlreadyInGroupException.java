package seedu.address.model.group.exceptions;

/**
 * Signals that an operation would result in adding a duplicate student to a group.
 */
public class AlreadyInGroupException extends RuntimeException {

    /**
     * Constructs an {@code AlreadyInGroupException} with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public AlreadyInGroupException(String message) {
        super(message);
    }
}
