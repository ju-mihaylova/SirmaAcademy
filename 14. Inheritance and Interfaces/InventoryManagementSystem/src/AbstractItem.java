public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {
    private String name;
    private String category;
    private double price;
    private boolean breakable;
    private boolean perishable;
    private boolean broken;
    private double perishabilityDegree;

    public AbstractItem(String name, String category, double price, boolean breakable, boolean perishable) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.breakable = breakable;
        this.perishable = perishable;
        this.broken = false;
        this.perishabilityDegree = 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getItemDetails() {
        return "Name: " + name + ", Category: " + category + ", Price: " + price;
    }

    @Override
    public abstract double calculateValue();

    @Override
    public void displayItemDescription() {
        System.out.println("Item: " + name + " | Category: " + category + " | Price: " + price);
    }

    @Override
    public void setItemCategory(String category) {
        this.category = category;
    }

    @Override
    public String getItemCategory() {
        return category;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public void handleItemBreakage() {
        if (isBreakable()) {
            breakItem();
            System.out.println(name + " has broken.");
        }
    }

    @Override
    public boolean hasBroken() {
        return broken;
    }

    @Override
    public void breakItem() {
        this.broken = true;
    }

    @Override
    public boolean isPerishable() {
        return perishable;
    }

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

    protected abstract void expireItem();

    // Getter and setter for perishability degree
    @Override
    public double getPerishabilityDegree() {
        return perishabilityDegree;
    }

    @Override
    public void setPerishabilityDegree(double degree) {
        this.perishabilityDegree = degree;
    }

    @Override
    public void setItemPrice(double price) {
        this.price = price;
    }

    @Override
    public double getItemPrice() {
        return price;
    }

}
