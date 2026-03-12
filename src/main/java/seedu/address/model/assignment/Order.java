package seedu.address.model.assignment;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the order for an Assignment
 * Guarantees: immutable; is valid as declared in {@link #isValidOrder(int)}
 */
public class Order {

    public static final String MESSAGE_CONSTRAINTS = "Order can take any integer between 0-999,"
            + " and it should not be blank";

    public final int order;

    /**
     * Constructs a {@code Order}.
     *
     * @params order A valid order.
     */
    public Order(int order) {
        checkArgument(isValidOrder(order), MESSAGE_CONSTRAINTS);
        this.order = order;
    }

    public static boolean isValidOrder(int order) {
        return (order >= 0 && order <= 999);
    }

    @Override
    public String toString() {
        return String.valueOf(order);
    }

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

    @Override
    public int hashCode() {
        return Integer.hashCode(order);
    }
}
