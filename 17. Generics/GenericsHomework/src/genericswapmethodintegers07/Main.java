package genericswapmethodintegers07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Box<Integer>> boxList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int line = Integer.parseInt(scanner.nextLine());
            Box<Integer> box = new Box<>(line);
            boxList.add(box);
        }

        int[] indexes = Arrays.stream(scanner.nextLine()
                        .split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        int firstIndex = indexes[0];
        int secondIndex = indexes[1];
        Box.swapItems(boxList, firstIndex, secondIndex);

        boxList.forEach(System.out::println);

    }
}
