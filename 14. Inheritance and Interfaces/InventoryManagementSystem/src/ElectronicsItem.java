import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ElectronicsItem extends InventoryItem {
    private int guaranteePeriod;
    private LocalDate addedDate;

    public ElectronicsItem(String name, String category, double price, double quantity, int guaranteePeriod) {
        super(name, category, price, true, false, quantity);
        this.guaranteePeriod = guaranteePeriod;
        this.addedDate = LocalDate.now();
    }

    public int getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(int guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }

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

    @Override
    public String getItemDetails() {
        return super.getItemDetails() + ", Guarantee Period: " + guaranteePeriod + " months";
    }
}
