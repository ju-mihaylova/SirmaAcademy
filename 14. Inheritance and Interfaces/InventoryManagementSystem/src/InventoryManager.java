import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private List<InventoryItem> inventoryItems;

    public InventoryManager() {
        inventoryItems = new ArrayList<>();
    }

    public void addItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    public boolean removeItem(int itemId) {
        return inventoryItems.removeIf(item -> item.getItemId() == itemId);
    }

    public void displayItems() {
        for(InventoryItem item : inventoryItems) {
            System.out.println(item.getItemDetails());
        }
    }

    public boolean isEmpty() {
        return inventoryItems.isEmpty();
    }

    public List<InventoryItem> getItems() {
        return inventoryItems;
    }

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
