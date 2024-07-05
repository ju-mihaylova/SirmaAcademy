package genericscale03;

public class Main {

    public static void main(String[] args) {

        Scale<String> scale = new Scale("admin", "user");
        System.out.println(scale.getHeavier());

        Scale<Integer> scale2 = new Scale(100, 100);
        System.out.println(scale2.getHeavier());
    }
}
