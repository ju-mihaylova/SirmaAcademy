package customlist10;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CustomList<String> strings = new CustomList<>();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        while(!line.equals("end")) {
            String[] parts = line.split("\\s+");
            String command = parts[0];

            switch(command) {
                case "Add": {
                    String element = parts[1];
                    strings.add(element);
                    break;
                }
                case "Remove": {
                    int index = Integer.parseInt(parts[1]);
                    strings.remove(index);
                    break;
                }
                case "Contains": {
                    String element = parts[1];
                    System.out.println(strings.contains(element));
                    break;
                }
                case "Swap": {
                    int firstIndex = Integer.parseInt(parts[1]);
                    int secondIndex = Integer.parseInt(parts[2]);
                    strings.swap(firstIndex, secondIndex);
                    break;
                }
                case "Greater": {
                    String element = parts[1];
                    System.out.println(strings.countGreaterThan(element));
                    break;
                }
                case "Max": {
                    System.out.println(strings.getMax());
                    break;
                }
                case "Min": {
                    System.out.println(strings.getMin());
                    break;
                }
                case "Print": {
                    for (int i = 0; i < strings.size(); i++) {
                        System.out.println(strings.get(i));
                    }
                }


            }

            line = scanner.nextLine();
        }
    }
}
