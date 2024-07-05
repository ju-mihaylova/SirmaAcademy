package jar01;

public class Main {

    public static void main(String[] args) {

        Jar<Integer> numbers = new Jar<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println(numbers.size());
        System.out.println("-----");

        while(numbers.size() > 0) {
            System.out.println(numbers.remove());
        }

        System.out.println("-----");
        System.out.println(numbers.size());
    }
}
