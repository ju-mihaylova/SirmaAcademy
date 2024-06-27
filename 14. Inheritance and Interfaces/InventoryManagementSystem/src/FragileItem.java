/**
 * The FragileItem class is a subclass of the InventoryItem class.
 * This class serves as a template for creating fragile items in the inventory management system.
 */
public class FragileItem extends InventoryItem {

    private double weight;

    /**
     * Constructor for creating a fragile item with specified attributes.
     *
     * @param name The name of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param quantity The quantity of the item.
     * @param weight The weight of the item.
     */
    public FragileItem(String name, String category, double price, double quantity, double weight) {
        super(name, category, price, true, false, quantity);
        this.weight = weight;
    }

    /**
     * Constructor for creating a fragile item with specified attributes.
     *
     * @param name The name of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param itemId The id of the item.
     * @param quantity The quantity of the item.
     * @param weight The weight of the item.
     */
    public FragileItem(String name, String category, double price, int itemId, double quantity, double weight) {
        super(name, category, price, true, false, itemId, quantity);
        this.weight = weight;
    }

    /**
     * Gets the weight of the item.
     *
     * @return The weight of the item.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the item.
     *
     * @param weight The weight of the item.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Calculates the value of the item.
     *
     * @return The calculated value of the item.
     */
    @Override
    public double calculateValue() {
        if (hasBroken()) {
            return 0;
        } else {
            double currentValue = getItemPrice() * 0.1 * weight;
            return currentValue > 0 ? currentValue : 0;
        }

    }

    /**
     * Gets the details of the item in a formatted string.
     *
     * @return A string containing item details.
     */
    @Override
    public String getItemDetails() {
        return super.getItemDetails() + " | " + weight;
    }
}
