package seedu.address.model.group.exceptions;

/**
 * Signals that an operation would result in adding a group that already exists.
 */
public class GroupAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a {@code GroupAlreadyExistsException} with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public GroupAlreadyExistsException(String message) {
        super(message);
    }
}
