/**
 * The Sellable interface represents items that can be sold.
 * This interface includes methods for setting and getting item prices.
 */
public interface Sellable {
    /**
     * Sets the price of the item.
     *
     * @param price The price to set.
     */
    void setItemPrice(double price);

    /**
     * Gets the price of the item.
     */
    double getItemPrice();
}
