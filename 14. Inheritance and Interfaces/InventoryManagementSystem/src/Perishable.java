public interface Perishable {
    boolean isPerishable();
    double getPerishabilityDegree();
    void setPerishabilityDegree(double degree);
    void handleItemExpiration();
}
