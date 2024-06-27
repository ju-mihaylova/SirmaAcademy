import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The ElectronicsItem class is a subclass of the InventoryItem class.
 * This class serves as a template for creating electronic items in the inventory management system.
 */
public class ElectronicsItem extends InventoryItem {
    private int guaranteePeriod;
    private LocalDate addedDate;

    /**
     * Constructor for creating an electronic item with specified attributes.
     *
     * @param name The name of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param quantity The quantity of the item.
     * @param guaranteePeriod The guarantee period of the item.
     */
    public ElectronicsItem(String name, String category, double price, double quantity, int guaranteePeriod) {
        super(name, category, price, true, false, quantity);
        this.guaranteePeriod = guaranteePeriod;
        this.addedDate = LocalDate.now();
    }

    /**
     *  Constructor for creating an electronic item with specified attributes.
     *
     * @param name The name of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param itemId The itemId of the item.
     * @param quantity The quantity of the item.
     * @param guaranteePeriod The guarantee period of the item.
     */
    public ElectronicsItem(String name, String category, double price, int itemId, double quantity, int guaranteePeriod) {
        super(name, category, price, true, false, itemId, quantity);
        this.guaranteePeriod = guaranteePeriod;
        this.addedDate = LocalDate.now();
    }

    /**
     * Gets the guarantee period of the item.
     *
     * @return The guarantee period of the item.
     */
    public int getGuaranteePeriod() {
        return guaranteePeriod;
    }

    /**
     * Sets the guarantee period of the item.
     *
     * @param guaranteePeriod The guarantee period of the item.
     */
    public void setGuaranteePeriod(int guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
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
            long monthsPassed = ChronoUnit.MONTHS.between(addedDate, LocalDate.now());
            double depreciationRate = (double) monthsPassed / guaranteePeriod;
            double currentValue = getItemPrice() * (1 - depreciationRate);
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
        return super.getItemDetails() + " | " + guaranteePeriod;
    }
}
