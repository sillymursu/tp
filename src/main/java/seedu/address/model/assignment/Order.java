package seedu.address.model.assignment;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the order for an Assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidOrder(int)}.
 */
public class Order {

    public static final String MESSAGE_CONSTRAINTS = "Order can take any integer between 0-999,"
            + " and it should not be blank";

    public final int order;

    /**
     * Constructs a {@code Order}.
     *
     * @param order A valid order.
     */
    public Order(int order) {
        checkArgument(isValidOrder(order), MESSAGE_CONSTRAINTS);
        this.order = order;
    }

    /**
     * Returns true if a given integer is a valid order.
     *
     * @param order The integer to validate.
     * @return {@code true} if {@code order} is between 0 and 999 inclusive, {@code false} otherwise.
     */
    public static boolean isValidOrder(int order) {
        return (order >= 0 && order <= 999);
    }

    /**
     * Returns the string representation of this order.
     *
     * @return The order as a string.
     */
    @Override
    public String toString() {
        return String.valueOf(order);
    }

    /**
     * Returns true if both orders have the same value.
     *
     * @param other The object to compare against.
     * @return {@code true} if this order is equal to the other object, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return order == (otherOrder.order);
    }

    /**
     * Returns the hash code value of this order.
     *
     * @return The hash code of this order.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(order);
    }
}
