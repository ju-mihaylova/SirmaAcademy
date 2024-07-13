package OCP_02.DiscountCalculator;

import java.util.EnumSet;

public enum UserType {
    STUDENT(0.1),
    SENIOR(0.2);

    private final double value;
    private UserType(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public static boolean contains(UserType type) {
        return EnumSet.allOf(UserType.class).contains(type);
    }
}
