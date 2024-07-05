package hotel.audit;

import hotel.utils.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Audit {


    private Audit() {
    }

    public static void logEvent(String eventType, String message) {
        String timestamp = LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER);
        String logMessage = String.format("%s [%s] - %s", timestamp, eventType, message);

        try (FileWriter fileWriter = new FileWriter(Constants.AUDIT_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(logMessage);
        } catch (IOException e) {
            System.out.println("Error writing to audit log: " + e.getMessage());
        }
    }

    public static void logInfo(String message) {
        logEvent("INFO", message);
    }
}

