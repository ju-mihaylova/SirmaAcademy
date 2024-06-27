/**
 * The Categorizable inteface represents items that can be categorized.
 * This interface includes methods for setting and getting the item category.
 */
public interface Categorizable {
    /**
     * Sets the category of the item.
     *
     * @param category The category to set.
     */
    void setItemCategory(String category);

    /**
     * Gets the category of the item.
     */
    String getItemCategory();
}
