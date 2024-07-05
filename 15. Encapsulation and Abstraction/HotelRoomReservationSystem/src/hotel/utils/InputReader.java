package hotel.utils;

import java.util.Scanner;

public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int readInteger(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    public static boolean readBoolean(String message) {
        System.out.print(message);
        return Boolean.parseBoolean(scanner.nextLine());
    }
}
