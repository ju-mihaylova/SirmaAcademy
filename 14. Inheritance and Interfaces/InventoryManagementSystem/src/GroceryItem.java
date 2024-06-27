/**
 * The GroceryItem class is a subclass of the InventoryItem class.
 * This class serves as a template for creating grocery items in the inventory management system.
 */
public class GroceryItem extends InventoryItem {
    private double freshnessCoefficient;

    /**
     * Constructor for creating a grocery item with specified attributes.
     *
     * @param name The name of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param quantity The quantity of the item.
     * @param freshnessCoefficient The freshnessCoefficient of the item.
     */
    public GroceryItem(String name, String category, double price, double quantity, double freshnessCoefficient) {
        super(name, category, price, false, true, quantity);
        this.freshnessCoefficient = freshnessCoefficient;
    }

    /**
     * Constructor for creating a grocery item with specified attributes.
     *
     * @param name The name of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param itemId The id of the item.
     * @param quantity The quantity of the item.
     * @param freshnessCoefficient The freshnessCoefficient of the item.
     */
    public GroceryItem(String name, String category, double price, int itemId, double quantity, double freshnessCoefficient) {
        super(name, category, price, false, true, itemId, quantity);
        this.freshnessCoefficient = freshnessCoefficient;
    }

    /**
     * Gets the freshness coefficient of the item.
     *
     * @return The freshness coefficient of the item.
     */
    public double getFreshnessCoefficient() {
        return freshnessCoefficient;
    }

    /**
     * Calculates the value of the item.
     *
     * @return The calculated value of the item.
     */
    @Override
    public double calculateValue() {
        double currentValue = getItemPrice() * (1 - getPerishabilityDegree()) * getQuantity() * freshnessCoefficient;
        return currentValue > 0 ? currentValue : 0;
    }

    /**
     * Gets the details of the item in a formatted string.
     *
     * @return A string containing item details.
     */
    @Override
    public String getItemDetails() {
        return super.getItemDetails() + " | " + freshnessCoefficient;
    }
}
