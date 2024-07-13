package OCP_02.DiscountCalculator;

public class DiscountCalculator {
    public double calculateDiscount(UserType type, double price) {
        if (UserType.contains(type)) {
            return price * type.getValue();
        }

        return price;
    }
}
