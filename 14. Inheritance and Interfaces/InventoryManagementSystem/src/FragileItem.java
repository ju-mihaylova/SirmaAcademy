public class FragileItem extends InventoryItem {

    private double weight;

    public FragileItem(String name, String category, double price, double quantity, double weight) {
        super(name, category, price, true, false, quantity);
        this.weight = weight;
    }
    public FragileItem(String name, String category, double price, int itemId, double quantity, double weight) {
        super(name, category, price, true, false, itemId, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double calculateValue() {
        if (hasBroken()) {
            return 0;
        } else {
            double currentValue = getItemPrice() * 0.1 * weight;
            return currentValue > 0 ? currentValue : 0;
        }

    }

    @Override
    public String getItemDetails() {
        return super.getItemDetails() + " | " + weight;
    }
}
