/**
 * The Payment class provides a means to make payments of a given amount via a specific payment method.
 */
public class Payment {
    private double amount;
    private String method;

    /**
     * Constructor for creating a Payment with specified attributes.
     *
     * @param amount The amount of the payment.
     * @param method The method of the payment.
     */
    public Payment(double amount, String method) {
        this.amount = amount;
        this.method = method;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return The amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the payment method of the transaction.
     *
     * @return The payment method.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Gives details about the transaction.
     *
     * @return A formatted string with transaction details.
     */
    @Override
    public String toString() {
        return "Payment of " + amount + " via " + method;
    }

}
