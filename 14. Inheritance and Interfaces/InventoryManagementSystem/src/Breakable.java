/**
 * The Breakable interface indicates items that can break.
 * This interface includes methods for checking if an item is breakable and for handling item breakage.
 */
public interface Breakable {
    /**
     * Checks if the item is breakable.
     */
    boolean isBreakable();

    /**
     * Checks if the item has broken.
     */
    boolean hasBroken();

    /**
     * Marks the item as broken.
     */
    void breakItem();

    /**
     * Handles the breakage of the item.
     */
    void handleItemBreakage();
}
