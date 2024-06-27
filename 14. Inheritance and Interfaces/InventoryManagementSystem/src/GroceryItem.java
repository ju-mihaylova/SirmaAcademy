public class GroceryItem extends InventoryItem {
    private double freshnessCoefficient;

    public GroceryItem(String name, String category, double price, double quantity, double freshnessCoefficient) {
        super(name, category, price, false, true, quantity);
        this.freshnessCoefficient = freshnessCoefficient;
    }
    public GroceryItem(String name, String category, double price, int itemId, double quantity, double freshnessCoefficient) {
        super(name, category, price, false, true, itemId, quantity);
        this.freshnessCoefficient = freshnessCoefficient;
    }

    public double getFreshnessCoefficient() {
        return freshnessCoefficient;
    }

    @Override
    public double calculateValue() {
        double currentValue = getItemPrice() * (1 - getPerishabilityDegree()) * getQuantity() * freshnessCoefficient;
        return currentValue > 0 ? currentValue : 0;
    }

    @Override
    public String getItemDetails() {
        return super.getItemDetails() + " | " + freshnessCoefficient;
    }
}
