package LSP_03.Bird;

public class FlyingBird extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Flying...");
    }
}
