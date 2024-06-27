/**
 * The Item interface represents items in the inventory.
 * This interface includes methods for getting item details, calculating value, adn displaying the item's description;
 */
public interface Item {
    /**
     * Gets the details of the item in a formatted string.
     */
    String getItemDetails();

    /**
     * Calculates the value of the item.
     */
    double calculateValue();

    /**
     * Displays the description of the item.
     */
    void displayItemDescription();
}
