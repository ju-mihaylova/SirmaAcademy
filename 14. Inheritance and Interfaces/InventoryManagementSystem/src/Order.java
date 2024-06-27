import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private List<InventoryItem> items;
    private Payment payment;

    public Order() {
        this.orderId = ++orderCounter;
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void addItem(InventoryItem item) {
        this.items.add(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getItemPrice() * items.get(i).getQuantity();
        }
        return total;
    }

    public void processPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

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

