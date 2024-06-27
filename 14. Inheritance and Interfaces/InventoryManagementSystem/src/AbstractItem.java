/**
 * The AbstractItem class provides a base implementation for items in an inventory system.
 * This class implements multiple interfaces: Item, Categorizable, Breakable, Perishable, and Sellable.
 * It provides common functionality for these interfaces and serves as a foundation for concrete item classes.
 */
public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {
    private String name;
    private String category;
    private double price;
    private boolean breakable;
    private boolean perishable;
    private boolean broken;
    private double perishabilityDegree;

    /**
     * Constructor for creating an AbstractItem with specified attributes.
     *
     * @param name The name of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param breakable Whether the item is breakable.
     * @param perishable Whether the item is perishable.
     */
    public AbstractItem(String name, String category, double price, boolean breakable, boolean perishable) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.breakable = breakable;
        this.perishable = perishable;
        this.broken = false;
        this.perishabilityDegree = 0;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the details of the item in a formatted string.
     *
     * @return A string containing item details.
     */
    @Override
    public String getItemDetails() {
        return name + " | " + category + " | " + price + " | " + breakable + " | " +
                perishable + " | " + perishabilityDegree;
    }

    /**
     * Calculates the value of the item.
     * This method must be implemented by subclasses.
     *
     * @return The calculated value of the item.
     */
    @Override
    public abstract double calculateValue();

    /**
     * Displays the description of the item.
     */
    @Override
    public void displayItemDescription() {
        System.out.println("Item: " + name + " | Category: " + category + " | Price: " + price);
    }

    /**
     * Sets the category of the item.
     *
     * @param category The category to set.
     */
    @Override
    public void setItemCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the category of the item.
     *
     * @return The category of the item.
     */
    @Override
    public String getItemCategory() {
        return category;
    }

    /**
     * Checks if the item is breakable.
     *
     * @return True if the item is breakable, false otherwise.
     */
    @Override
    public boolean isBreakable() {
        return breakable;
    }

    /**
     * Handles the breakage of the item if it is breakable.
     */
    @Override
    public void handleItemBreakage() {
        if (isBreakable()) {
            breakItem();
            System.out.println(name + " has broken.");
        }
    }

    /**
     * Checks if the item has broken.
     *
     * @return True if the item has broken, false otherwise.
     */
    @Override
    public boolean hasBroken() {
        return broken;
    }

    /**
     * Marks the item as broken.
     */
    @Override
    public void breakItem() {
        this.broken = true;
    }

    /**
     * Checks if the item is perishable.
     *
     * @return True if the item is perishable, false otherwise.
     */
    @Override
    public boolean isPerishable() {
        return perishable;
    }

    /**
     * Handles the expiration of the item if it is perishable.
     * Increases the perishability degree of the item until it reaches a threshold.
     */
    @Override
    public void handleItemExpiration() {
        if (isPerishable()) {
            if (perishabilityDegree >= 1.0) {
                expireItem();
            } else {
                perishabilityDegree += 0.1;
            }
            System.out.println(name + " has expired to degree: " + perishabilityDegree);
        }
    }

    /**
     * Expires the item.
     * This method must be implemented by subclasses to define specific expiration behavior.
     */
    protected abstract void expireItem();

    /**
     * Gets the perishability degree of the item.
     *
     * @return The perishability degree of the item.
     */
    @Override
    public double getPerishabilityDegree() {
        return perishabilityDegree;
    }

    /**
     * Sets the perishability degree of the item.
     *
     * @param degree The perishability degree to set.
     */
    @Override
    public void setPerishabilityDegree(double degree) {
        this.perishabilityDegree = degree;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The price to set.
     */
    @Override
    public void setItemPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    @Override
    public double getItemPrice() {
        return price;
    }

}
