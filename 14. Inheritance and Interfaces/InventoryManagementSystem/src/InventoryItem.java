/**
 * The InventoryItem class is a superclass that extends the abstract AbstractItem class and implements the Cloneable interface.
 * It is a basis for creating more specific Item subclasses.
 */
public class InventoryItem extends AbstractItem implements Cloneable {
    private static int idCounter = 0;
    private final int itemId;
    private double quantity;

    /**
     * Constructor for creating an inventory item.
     *
     * @param name The name of the inventory item.
     * @param category The category of the inventory item.
     * @param price The price of the inventory item.
     * @param breakable Whether the inventory item is breakable.
     * @param perishable Whether the inventory item is perishable.
     * @param quantity The quantity of the inventory item.
     */
    public InventoryItem(String name, String category, double price, boolean breakable, boolean perishable, double quantity) {
        super(name, category, price, breakable, perishable);
        this.quantity = quantity;
        this.itemId = ++idCounter;
    }

    /**
     * Constructor for creating an inventory item.
     *
     * @param name The name of the inventory item.
     * @param category The category of the inventory item.
     * @param price The price of the inventory item.
     * @param breakable Whether the inventory item is breakable.
     * @param perishable Whether the inventory item is perishable.
     * @param itemId The itemId of the inventory item.
     * @param quantity The quantity of the inventory item.
     */
    public InventoryItem(String name, String category, double price, boolean breakable, boolean perishable, int itemId, double quantity) {
        super(name, category, price, breakable, perishable);
        this.quantity = quantity;
        this.itemId = itemId;
        if (itemId > idCounter) {
            idCounter = itemId;
        }
    }

    /**
     * Gets the itemId of the inventory item.
     *
     * @return The itemId of the inventory item.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Gets the quantity of the inventory item.
     *
     * @return The quantity of the inventory item.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the inventory item.
     *
     * @param quantity The quantity of the inventory item.
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Expires the item.
     */
    @Override
    protected void expireItem() {
        if (isPerishable()) {
            setPerishabilityDegree(getPerishabilityDegree() + 1);
        }
    }

    /**
     * Calculates the value of the inventory item.
     *
     * @return The calculated value of the inventory item.
     */
    @Override
    public double calculateValue() {
         return getItemPrice() * quantity;
    }

    /**
     * Gets the details of the inventory item in a formatted string.
     *
     * @return A string containing inventory item details.
     */
    @Override
    public String getItemDetails() {
        return super.getItemDetails() + " | " + itemId + " | " + quantity;
    }

    /**
     * Clones the inventory item.
     *
     * @return A clone of the inventory item.
     */
    @Override
    public InventoryItem clone() {
        try {
            return (InventoryItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone not supported.");
        }
    }
}
