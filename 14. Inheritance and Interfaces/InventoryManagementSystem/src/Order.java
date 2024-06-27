import java.util.ArrayList;
import java.util.List;

/**
 * The Order class provides a way to create an order, add items to an order, calculate total and process a payment.
 */
public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private List<InventoryItem> items;
    private Payment payment;

    /**
     * Constructor for creating an order.
     */
    public Order() {
        this.orderId = ++orderCounter;
        this.items = new ArrayList<>();
    }

    /**
     * Get the id of the order.
     *
     * @return The id of the order.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Get the items of the order.
     *
     * @return The items of the order.
     */
    public List<InventoryItem> getItems() {
        return items;
    }

    /**
     * Add an item to the order.
     *
     * @param item The item to add.
     */
    public void addItem(InventoryItem item) {
        this.items.add(item);
    }

    /**
     * Calculate the total amount of the order.
     *
     * @return The calculated total amount of the order.
     */
    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getItemPrice() * items.get(i).getQuantity();
        }
        return total;
    }

    /**
     * Process the payment.
     *
     * @param payment The payment to process.
     */
    public void processPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Gets the payment of the order.
     *
     * @return The payment of the order.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Overrides the toString() method with details about the order.
     *
     * @return Details about the order.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order ID: " + orderId + "\nItems:\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).getName())
                    .append(" - Quantity: ")
                    .append(items.get(i).getQuantity())
                    .append(" - Price per unit: ")
                    .append(items.get(i).getItemPrice())
                    .append("\n");
        }
        sb.append("Total: ").append(calculateTotal()).append("\n");
        if (payment != null) {
            sb.append(payment.toString()).append("\n");
        }
        return sb.toString();
    }
}

