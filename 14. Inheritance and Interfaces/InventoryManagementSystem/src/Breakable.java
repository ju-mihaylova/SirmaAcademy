public interface Breakable {
    boolean isBreakable();
    boolean hasBroken();
    void breakItem();
    void handleItemBreakage();
}
