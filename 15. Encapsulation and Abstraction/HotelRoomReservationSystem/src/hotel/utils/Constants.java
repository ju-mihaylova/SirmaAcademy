package hotel.utils;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String ROOMS_FILE_PATH_PREFIX = "src/resources/nomenclature/";
    public static final String BOOKINGS_FILE_PATH = "src/resources/bookings.csv";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String AUDIT_FILE_PATH = "src/resources/audit.log";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Constants() {
    }
}
