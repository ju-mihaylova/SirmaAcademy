package genericarraycreator02;

public class Main {

    public static void main(String[] args) {

        String[] strings = ArrayCreator.create(5, "none");
        Integer[] numbers = ArrayCreator.create(Integer.class, 5, 0);
        for (String string : strings) {
            System.out.println(string);
        }

        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}
