import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdersManager {

    private ArrayList<Order> orders;
    private static final Scanner scanner = new Scanner(System.in);

    public OrdersManager() {
        orders = new ArrayList<>();
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void placeOrder(List<InventoryItem> inventoryItems) {

        Order order = new Order();

        while (true) {

            System.out.print("Enter item ID to add to order (or 'done' to finish): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            int id = Integer.parseInt(input);
            InventoryItem itemToOrder = null;
            for (InventoryItem item : inventoryItems) {
                if (item.getItemId() == id) {
                    itemToOrder = item;
                    break;
                }
            }

            if (itemToOrder != null) {
                System.out.println("Enter quantity to order: ");
                double quantity = Double.parseDouble(scanner.nextLine());
                if (itemToOrder.getQuantity() >= quantity) {
                    try {
                        InventoryItem cloneItem = itemToOrder.clone();
                        cloneItem.setQuantity(quantity);
                        order.addItem(cloneItem);
                        itemToOrder.setQuantity(itemToOrder.getQuantity() - quantity);
                    } catch (AssertionError e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Insufficient quantity in stock.");
                }
            } else {
                System.out.println("Item with ID " + id + " not found.");
            }
        }

        double totalPrice = 0;
        for(InventoryItem item : order.getItems()) {
            totalPrice += item.getItemPrice() * item.getQuantity();
        }

        System.out.println("How would you pay: paypal, card, cash?");
        String method = scanner.nextLine();

        order.processPayment(new Payment(totalPrice, method));
        orders.add(order);
        System.out.println("Order placed successfully.");
    }

    public void displayOrders() {
        System.out.println("Total orders: " + orders.size());
        if (orders.isEmpty()) {
            System.out.println("No orders placed.");
        } else {
            for(Order order : orders) {
                System.out.println(order.toString());
            }
        }

    }
}
