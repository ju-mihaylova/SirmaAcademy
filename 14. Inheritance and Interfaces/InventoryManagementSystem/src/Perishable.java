/**
 * The Perishable interface represents items that can perish.
 * This interface includes methods for checking if an item is perishable and for handling item expiration.
 */
public interface Perishable {
    /**
     * Checks if the item is perishable.
     */
    boolean isPerishable();

    /**
     * Gets the perishability degree of the item.
     */
    double getPerishabilityDegree();

    /**
     * Sets the perishability degree of the item.
     *
     * @param degree The perishability degree to set.
     */
    void setPerishabilityDegree(double degree);

    /**
     * Handles the expiration of the item.
      */
    void handleItemExpiration();
}
