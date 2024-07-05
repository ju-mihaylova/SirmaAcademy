package genericcountmethoddoubles09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Box<Double>> boxList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Double line = Double.parseDouble(scanner.nextLine());
            Box<Double> box = new Box<>(line);
            boxList.add(box);
        }

        Box<Double> doubleBox = new Box<>(Double.parseDouble(scanner.nextLine()));
        int count = Box.count(boxList, doubleBox);
        System.out.println(count);
    }
}
