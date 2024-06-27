import java.util.Scanner;

public class CLI {
    private static InventoryManager inventoryItems = new InventoryManager();
    private static OrdersManager ordersManager = new OrdersManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItemByID();
                    break;
                case 3:
                    displayItems();
                    break;
                case 4:
                    categorizeItems();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    saveInventory();
                    break;
                case 7:
                    loadInventory();
                    break;
                case 8:
                    displayOrders();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Inventory Management System Menu:");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item by ID");
        System.out.println("3. Display Items");
        System.out.println("4. Categorize Items");
        System.out.println("5. Place Order");
        System.out.println("6. Save Inventory");
        System.out.println("7. Load Inventory");
        System.out.println("8. Display Orders");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addItem() {
        System.out.println("Enter item type (Electronics, Grocery, Fragile): ");
        String type = scanner.nextLine();

        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter item category: ");
        String category = scanner.nextLine();

        System.out.print("Enter item price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter item quantity: ");
        double quantity = Double.parseDouble(scanner.nextLine());

        InventoryItem item;
        switch (type.toLowerCase()) {
            case "electronics":
                System.out.print("Enter guarantee period (months): ");
                int guaranteePeriod = Integer.parseInt(scanner.nextLine());
                item = new ElectronicsItem(name, category, price, quantity, guaranteePeriod);
                break;
            case "grocery":
                System.out.print("Enter freshnessCoefficient (between 0 and 1): ");
                double freshnessCoefficient = Double.parseDouble(scanner.nextLine());
                item = new GroceryItem(name, category, price, quantity, freshnessCoefficient);
                break;
            case "fragile":
                System.out.print("Enter item weight: ");
                double weight = Double.parseDouble(scanner.nextLine());
                item = new FragileItem(name, category, price, quantity, weight);
                break;
            default:
                System.out.println("Invalid item type. Item not added.");
                return;
        }

        inventoryItems.addItem(item);
        System.out.println("Item added successfully.");
    }

    private static void removeItemByID() {
        System.out.print("Enter item ID to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean result = inventoryItems.removeItem(id);

        if (result) {
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    private static void displayItems() {
        if (inventoryItems.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            inventoryItems.displayItems();
        }
    }

    private static void categorizeItems() {
        System.out.print("Enter category to filter by: ");
        String category = scanner.nextLine();

        for (InventoryItem item : inventoryItems.getItems()) {
            if (item.getItemCategory().equalsIgnoreCase(category)) {
                System.out.println(item.getItemDetails());
            }
        }
    }

    private static void placeOrder() {
        ordersManager.placeOrder(inventoryItems.getItems());
    }

    private static void displayOrders() {
        ordersManager.displayOrders();
    }

    private static void saveInventory() {
        inventoryItems.saveInventory("inventory.txt");
    }

    private static void loadInventory() {
        inventoryItems.loadInventory("inventory.txt");
    }

}
