import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The InventoryManager class implements methods relating to managing the inventory such as adding, removing and displaying items, saving and loading the inventory.
 */
public class InventoryManager {

    private List<InventoryItem> inventoryItems;

    /**
     * Constructor for creating a list of inventory items.
     */
    public InventoryManager() {
        inventoryItems = new ArrayList<>();
    }

    /**
     * Adds an item to the list of inventory items.
     *
     * @param item The item to add to the list.
     */
    public void addItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    /**
     * Removes an item from the list of inventory items.
     *
     * @param itemId The id of the item to remove from the list.
     *
     * @return True if the item has been removed, false otherwise.
     */
    public boolean removeItem(int itemId) {
        return inventoryItems.removeIf(item -> item.getItemId() == itemId);
    }

    /**
     * Displays all items in the inventory in a formatted string.
     */
    public void displayItems() {
        for(InventoryItem item : inventoryItems) {
            System.out.println(item.getItemDetails());
        }
    }

    /**
     * Checks if the list of inventory items is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return inventoryItems.isEmpty();
    }

    /**
     * Gets the list of inventory items.
     *
     * @return The list of inventory items.
     */
    public List<InventoryItem> getItems() {
        return inventoryItems;
    }

    /**
     * Saves the inventory to a specified .txt file.
     *
     * @param filename The name of the file to which the inventory is saved.
     */
    public void saveInventory(String filename) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for(InventoryItem item: inventoryItems) {
                String line = item.getClass().getName() + " | " + item.getItemDetails();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Inventory saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the inventory from a specified .txt file.
     *
     * @param filename The name of the file from which the inventory is loaded.
     */
    public void loadInventory(String filename) {

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            inventoryItems.clear();
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String className = parts[0];
                String name = parts[1];
                String category = parts[2];
                double price = Double.parseDouble(parts[3]);
                int itemId = Integer.parseInt(parts[7]);
                double quantity = Double.parseDouble(parts[8]);
                if (className.equals("ElectronicsItem")) {
                    int guaranteePeriod = Integer.parseInt(parts[9]);
                    ElectronicsItem ei = new ElectronicsItem(name, category, price, itemId, quantity, guaranteePeriod);
                    inventoryItems.add(ei);
                } else if (className.equals("GroceryItem")) {
                    double freshnessCoefficient = Double.parseDouble(parts[9]);
                    GroceryItem gi = new GroceryItem(name, category, price, itemId, quantity, freshnessCoefficient);
                    inventoryItems.add(gi);
                } else if (className.equals("FragileItem")) {
                    double weight = Double.parseDouble(parts[9]);
                    FragileItem fi = new FragileItem(name, category, price, itemId, quantity, weight);
                    inventoryItems.add(fi);
                }
            }
            System.out.println("Inventory loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
