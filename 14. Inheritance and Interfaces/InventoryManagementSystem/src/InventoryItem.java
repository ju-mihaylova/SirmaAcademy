public class InventoryItem extends AbstractItem {
    private static int idCounter = 0;
    private final int itemId;
    private double quantity;
    public InventoryItem(String name, String category, double price, boolean breakable, boolean perishable, double quantity) {
        super(name, category, price, breakable, perishable);
        this.quantity = quantity;
        this.itemId = ++idCounter;
    }

    public int getItemId() {
        return itemId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    protected void expireItem() {
        if (isPerishable()) {
            setPerishabilityDegree(getPerishabilityDegree() + 1);
        }
    }

    @Override
    public double calculateValue() {
         return getItemPrice() * quantity;
    }

    @Override
    public String getItemDetails() {
        return super.getItemDetails() + ", ID: " + itemId + ", Quantity: " + quantity;
    }
}
